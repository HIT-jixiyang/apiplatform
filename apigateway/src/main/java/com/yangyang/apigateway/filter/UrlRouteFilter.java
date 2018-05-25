package com.yangyang.apigateway.filter;

import com.alibaba.fastjson.JSONObject;
import com.netflix.zuul.context.RequestContext;
import com.yangyang.apigateway.service.BillService;
import com.yangyang.entity.RequestBody;
import com.yangyang.pojo.entity.Api;
import com.yangyang.pojo.entity.ApiCategory;
import com.yangyang.pojo.service.ApiCategoryService;
import com.yangyang.pojo.service.ApiParamService;
import com.yangyang.pojo.service.ApiService;
import com.yangyang.utils.utils.BillItemID;
import com.yangyang.utils.utils.InputStreamUtil;
import com.yangyang.utils.utils.SerializeUtil;
import org.apache.http.*;
import org.apache.http.client.HttpClient;
import org.apache.http.client.RedirectStrategy;
import org.apache.http.client.config.RequestConfig;
import org.apache.http.client.methods.HttpUriRequest;
import org.apache.http.conn.ssl.NoopHostnameVerifier;
import org.apache.http.entity.ContentType;
import org.apache.http.entity.InputStreamEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.DefaultHttpRequestRetryHandler;
import org.apache.http.impl.client.HttpClientBuilder;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.impl.conn.PoolingHttpClientConnectionManager;
import org.apache.http.protocol.HttpContext;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.actuate.metrics.CounterService;
import org.springframework.cloud.netflix.zuul.filters.ProxyRequestHelper;
import org.springframework.cloud.netflix.zuul.filters.ZuulProperties;
import org.springframework.cloud.netflix.zuul.filters.route.SimpleHostRoutingFilter;
import org.springframework.stereotype.Component;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.util.StringUtils;

import javax.servlet.ServletInputStream;
import javax.servlet.http.HttpServletRequest;
import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.lang.reflect.Method;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLClassLoader;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@Component
class UrlRouteFilter extends SimpleHostRoutingFilter {
    private static final Logger LOGGER = LoggerFactory.getLogger(UrlRouteFilter.class);
    ProxyRequestHelper helper;
    ZuulProperties properties;
    @Autowired
    ApiParamService apiParamService;

    @Autowired
    CounterService counterService;
    @Autowired
    ApiCategoryService apiCategoryService;
    @Autowired
    ApiService apiService;
@Autowired
    BillService billService;
    @Override
    public String filterType() {
        return "route";
    }

    @Override
    public boolean shouldFilter() {
        return super.shouldFilter();
    }

    @Override
    public int filterOrder() {
        return super.filterOrder();
    }

    CloseableHttpClient httpClient;
    private boolean sslHostnameValidationEnabled;

    public UrlRouteFilter( ProxyRequestHelper helper, ZuulProperties properties) {
        super(helper, properties);
        System.out.println("进入自定义路由");
        this.helper = helper;
        this.properties = properties;
        this.sslHostnameValidationEnabled = properties.isSslHostnameValidationEnabled();
    }

    private String getVerb(HttpServletRequest request) {
        String sMethod = request.getMethod();
        return sMethod.toUpperCase();
    }

    private InputStream getRequestBody(HttpServletRequest request) {
        ServletInputStream requestEntity = null;

        try {
            requestEntity = request.getInputStream();
        } catch (IOException var4) {
            ;
        }

        return requestEntity;
    }

    private HttpHost getHttpHost(URL host) {
        HttpHost httpHost = new HttpHost(host.getHost(), host.getPort(), host.getProtocol());
        return httpHost;
    }

    private HttpResponse forwardRequest(HttpClient httpclient, HttpHost httpHost, HttpRequest httpRequest) throws IOException {

        return httpclient.execute(httpHost, httpRequest);
    }

    private HttpResponse forward(HttpClient httpclient, URL host,String verb, String uri, HttpServletRequest request, MultiValueMap<String, String> headers, MultiValueMap<String, String> params, InputStream requestEntity) throws Exception {
        Map<String, Object> info = this.helper.debug(verb, uri, headers, params, requestEntity);
      //  URL host = RequestContext.getCurrentContext().getRouteHost();
      //  URL host2=new URL("");
        System.out.println("host:" + host);
        HttpHost httpHost = this.getHttpHost(host);
        uri = StringUtils.cleanPath((host.getPath() + uri).replaceAll("/{2,}", "/"));
        int contentLength = request.getContentLength();
        ContentType contentType = null;
        if (request.getContentType() != null) {
            contentType = ContentType.parse(request.getContentType());
        }

        InputStreamEntity entity = new InputStreamEntity(requestEntity, (long) contentLength, contentType);
        HttpRequest httpRequest = this.buildHttpRequest(verb, uri, entity, headers, params);

        System.out.println(httpHost.getHostName() + " " + httpHost.getPort() + " " + httpHost.getSchemeName());
        HttpResponse zuulResponse = this.forwardRequest(httpclient, httpHost, httpRequest);

        this.helper.appendDebug(info, zuulResponse.getStatusLine().getStatusCode(), this.revertHeaders(zuulResponse.getAllHeaders()));
        return zuulResponse;
    }

    private MultiValueMap<String, String> revertHeaders(Header[] headers) {
        MultiValueMap<String, String> map = new LinkedMultiValueMap();
        Header[] var3 = headers;
        int var4 = headers.length;

        for (int var5 = 0; var5 < var4; ++var5) {
            Header header = var3[var5];
            String name = header.getName();
            if (!map.containsKey(name)) {
                map.put(name, new ArrayList());
            }

            ((List) map.get(name)).add(header.getValue());
        }

        return map;
    }

    @Override
    public Object run() {
        LOGGER.info("开始路由" + System.currentTimeMillis());

        RequestContext context = RequestContext.getCurrentContext();
        HttpServletRequest request = context.getRequest();
      /*  Integer timeout= (Integer) context.get("request_timeout");
        LOGGER.info(""+timeout);*/
        this.httpClient = newClient(10000);
        String servletPath = request.getServletPath();
        LOGGER.info("原始路径" + servletPath);


        MultiValueMap<String, String> headers = this.helper.buildZuulRequestHeaders(request);
        MultiValueMap<String, String> querys = this.helper.buildZuulRequestQueryParams(request);
        //  String api_id= (String) context.get("api_id");
        List<String> strategy = headers.get("strategy");
        URL host = null;
        host = getURL(servletPath + "/**", strategy.get(0), context);
        String verb = this.getVerb(request);
        InputStream requestEntity = this.getRequestBody(request);
        String app_id= headers.get("app_id").get(0);
        Api api = (Api) context.get("api");
        setCounter(api.getApi_id());

        LOGGER.info(api.toString());

        LOGGER.info(app_id+"验证通过");
        LOGGER.info("app_id:"+app_id+"  api_id:"+api.getApi_id()+"发起记账请求");
        //记账请求
        String bill_item_id= BillItemID.getBillItemID();
        context.set("bill_item_id",bill_item_id);
        context.set("startTime",System.currentTimeMillis());
        context.set("api_id",api.getApi_id());
        context.set("api_category");
        billService.bill(bill_item_id,api.getApi_id(),app_id);
        Boolean flag=true;
        if(flag) {
            try {
                String body = InputStreamUtil.InputStream2String(requestEntity);
                LOGGER.info(body);
                JSONObject jsonObject = JSONObject.parseObject(body);
                RequestBody requestBody = new RequestBody();
                requestBody.setHeader(headers);
                requestBody.setQuery(querys);
                requestBody.setBody(jsonObject);
                RequestBody customRequestBody = (RequestBody) getChangedRequestBodyByApiID(requestBody, api);
                headers = customRequestBody.getHeader();
                querys = customRequestBody.getQuery();
                body = JSONObject.toJSONString(customRequestBody.getBody());
                requestEntity = new ByteArrayInputStream(body.getBytes("UTF-8"));
            } catch (IOException e) {
                e.printStackTrace();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
      /*  List<ApiParam> apiParamList=apiParamService.getConstantParamByApiID(api_id);
     Set<String> keyset= headers.keySet();
      for(String key :keyset){
          LOGGER.info("key："+key);
      }*/
     /* Set<String> paramSet=querys.keySet();
        for ()*/
   /*  if(apiParamList!=null)
         for(ApiParam constantParam:apiParamList){
            switch (constantParam.getApi_after_param_position()){
                //header参数
                case 0: headers.add(constantParam.getApi_after_param_key(),constantParam.getApi_param_value());

            }
        }*/
        if (request.getContentLength() < 0) {
            context.setChunkedRequestBody();
        }
        String uri = this.helper.buildZuulRequestURI(request);
        LOGGER.info("uri:" + uri);
        this.helper.addIgnoredHeaders(new String[0]);
        try {
            HttpResponse response = this.forward(this.httpClient,host,  verb, uri, request, headers, querys, requestEntity);
            this.setResponse(response);
        } catch (Exception var9) {
            context.set("error.status_code", Integer.valueOf(500));
            context.set("error.exception", var9);
        }
        System.out.println("请求结束" + System.currentTimeMillis());
        return null;
    }

    private void setResponse(HttpResponse response) throws IOException {
        this.helper.setResponse(response.getStatusLine().getStatusCode(), response.getEntity() == null ? null : response.getEntity().getContent(), this.revertHeaders(response.getAllHeaders()));
    }

    @Override
    protected PoolingHttpClientConnectionManager newConnectionManager() {
        return super.newConnectionManager();
    }

    @Override
    protected HttpRequest buildHttpRequest(String verb, String uri, InputStreamEntity entity, MultiValueMap<String, String> headers, MultiValueMap<String, String> params) {
        return super.buildHttpRequest(verb, uri, entity, headers, params);
    }

    protected CloseableHttpClient newClient(Integer timeout) {
        RequestConfig requestConfig = RequestConfig.custom().setSocketTimeout(timeout).setConnectTimeout(10000).setCookieSpec("ignoreCookies").build();
        HttpClientBuilder httpClientBuilder = HttpClients.custom();

        if (!this.sslHostnameValidationEnabled) {
            httpClientBuilder.setSSLHostnameVerifier(NoopHostnameVerifier.INSTANCE);
        }
        return httpClientBuilder.setConnectionManager(this.newConnectionManager()).useSystemProperties().setDefaultRequestConfig(requestConfig).setRetryHandler(new DefaultHttpRequestRetryHandler(0, false)).setRedirectStrategy(new RedirectStrategy() {
            public boolean isRedirected(HttpRequest request, HttpResponse response, HttpContext context) throws ProtocolException {
                return false;
            }

            public HttpUriRequest getRedirect(HttpRequest request, HttpResponse response, HttpContext context) throws ProtocolException {
                return null;
            }
        }).build();
    }

    private URL getURL(String path, String strategy, RequestContext context) {
        ApiCategory apiCategory = apiCategoryService.getApiCategoryByPath(path);
        context.set("standardResponse",apiCategory.getApi_category_normal_response());
        Api api;
        switch (strategy) {
            case "0":
                api = apiService.getApiByCostAlgorithmAndApiCategoryID(apiCategory.getApi_category_id());
                try {
                    URL url = new URL(api.getApi_url());
                    context.put("api", api);
                    return url;
                } catch (MalformedURLException e) {
                    LOGGER.info("创建url出错");
                    e.printStackTrace();
                }

                break;
            case "1":
                api = apiService.getApiByTimeAlgorithmAndApiCategoryID(apiCategory.getApi_category_id());
                try {
                    URL url = new URL(api.getApi_url());
                    context.put("api", api);
                    return url;
                } catch (MalformedURLException e) {
                    LOGGER.info("创建url出错");
                    e.printStackTrace();
                }
                break;
            case "2":
                api = apiService.getApiByStableAlgorithmAndApiCategoryID(apiCategory.getApi_category_id());
                try {
                    URL url = new URL(api.getApi_url());
                    context.put("api", api);
                    return url;

                } catch (MalformedURLException e) {
                    LOGGER.info("创建url出错");
                    e.printStackTrace();
                }

                break;
            default:
                api = apiService.getApiByCostAlgorithmAndApiCategoryID(apiCategory.getApi_category_id());
                try {
                    URL url = new URL(api.getApi_url());
                    context.put("api", api);
                    return url;
                } catch (MalformedURLException e) {
                    LOGGER.info("创建url出错");
                    e.printStackTrace();
                }
        }
        return null;
    }

    public  static RequestBody getChangedRequestBodyByApiID(RequestBody request, Api api) throws Exception {
        String requestBody= SerializeUtil.serializeObject(request);
        String relativelyPath = System.getProperty("user.dir");
        URL[] urls = new URL[]{};
        MyClassLoader classLoader = new MyClassLoader(urls, ClassLoader.getSystemClassLoader());
        try {
            classLoader.addJar(new File("http://127.0.0.1:9999/group1/M00/00/00/wKgRgVr4P4OAI0KJAAAWdv2QN-s891.jar").toURI().toURL());
            Class<?> clazz = classLoader.loadClass("com.yangyang.utils.ParamMap");
            Method method = clazz.getDeclaredMethod("inboundParam2OutBoundParam", String.class);
            Object obj = clazz.newInstance();
            String request2 = (String) method.invoke(obj, requestBody);
            classLoader.close();
            return (RequestBody) SerializeUtil.stringSerializeObject(request2);
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }

    }

    static class MyClassLoader extends URLClassLoader {

        public MyClassLoader(URL[] urls) {
            super(urls);
        }

        public MyClassLoader(URL[] urls, ClassLoader parent) {
            super(urls, parent);
        }

        public void addJar(URL url) {
            this.addURL(url);
        }

    }
    private void setCounter(String api_id){
        counterService.increment("speed.second."+api_id);
        counterService.increment("speed.second.requesttotal");

    }

}
