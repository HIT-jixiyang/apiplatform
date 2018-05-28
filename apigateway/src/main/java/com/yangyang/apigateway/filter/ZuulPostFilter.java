package com.yangyang.apigateway.filter;

import com.netflix.zuul.ZuulFilter;
import com.netflix.zuul.context.RequestContext;
import com.netflix.zuul.http.ServletInputStreamWrapper;
import com.yangyang.apigateway.service.BillService;
import com.yangyang.pojo.entity.Api;
import com.yangyang.pojo.entity.BillItem;
import com.yangyang.pojo.entity.LeafMap;
import com.yangyang.pojo.service.JsonMapService;
import com.yangyang.pojo.service.LeafMapService;
import org.apache.http.Consts;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.util.Pair;
import org.springframework.stereotype.Component;
import org.springframework.util.StreamUtils;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.InputStream;
import java.nio.charset.Charset;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/*
*
 * @program: apiplatform
 * @description: 请求返回时的过滤器
 * @author: JiXiYang
 * @create: 2018-05-02 19:51
 *
*/

@Component
public class ZuulPostFilter extends ZuulFilter{
    private static final org.slf4j.Logger LOGGER = LoggerFactory.getLogger(ZuulPostFilter.class);
    @Autowired
    BillService billService;
    @Autowired
    JsonMapService jsonMapService;
    @Autowired
    LeafMapService leafMapService;
    @Override
    public String filterType() {
        return "post";
    }

    @Override
    public int filterOrder() {
        return 101;
    }

    @Override
    public boolean shouldFilter() {
        return true;
    }

    @Override
    public Object run() {

        RequestContext context = RequestContext.getCurrentContext();


        InputStream stream = context.getResponseDataStream();
        Api api= (Api) context.get("api");
      /*  HttpServletResponse response=context.getResponse();
        Collection<String> headerNames=  response.getHeaderNames();
        String type=response.getContentType();
        LOGGER.info(headerNames.toString()+"-------------"+type);*/
        List<com.netflix.util.Pair<String, String>> headers=  context.getZuulResponseHeaders();
        try {
            String body = StreamUtils.copyToString(stream, Charset.forName("UTF-8"));
            List<LeafMap> leafMapList=leafMapService.getLeafMapListByApiID(api.getApi_id());
            Map<Integer,Integer> map=new HashMap<>();
            for (LeafMap leafMap:leafMapList){
                map.put(leafMap.getStandard_leaf_id(),leafMap.getOrigin_leaf_id());
            }
            String standardBody=jsonMapService.getHandledString((String) context.get("standardResponse"),body,map);
         byte[] bytes=standardBody.getBytes(Consts.UTF_8);
        context.setResponseDataStream(new ServletInputStreamWrapper(bytes));
            try {
                billUpdate(context,body);
            } catch (IOException e) {
                e.printStackTrace();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }
private void billUpdate(RequestContext context,String body) throws IOException {
   // InputStream stream = context.getResponseDataStream();
  //  String body = StreamUtils.copyToString(stream, Charset.forName("UTF-8"));
   byte[] bytes=body.getBytes(Consts.UTF_8);
    HttpServletRequest request = context.getRequest();
    long startTime = (long) context.get("startTime");//请求的开始时间
    String api_id= (String) context.get("api_id");
    String app_id= (String) context.get("app_id");
    String bill_item_id= (String) context.get("bill_item_id");
    String response_code= String.valueOf(context.getResponseStatusCode());
    LOGGER.info(startTime+"---"+api_id+"----"+app_id+"----"+bill_item_id);
    if(api_id!=null&&app_id!=null&&bill_item_id!=null){
    BillItem billItem=new BillItem();
    billItem.setBill_item_id(bill_item_id);
    billItem.setApp_id(app_id);
    billItem.setApi_id(api_id);
    billItem.setResponse_code(response_code);
   billItem.setResponse_size(bytes.length);
    long duration=System.currentTimeMillis() - startTime;//请求耗时
    LOGGER.info("请求路径"+request.getServletPath()+" --- "+"duration:"+" "+duration);
    Float request_time= new Float(duration/1000.0);
    billItem.setRequest_time(request_time);
    billService.updateBill(billItem);
}

}
}
