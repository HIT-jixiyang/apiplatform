package com.yangyang.monitor.utils;

import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.config.RequestConfig;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
@Component
public class HTTPUtils {

    public static HttpResponse doget(String url) throws IOException {
        CloseableHttpClient httpCilent = HttpClients.custom().build();
        RequestConfig requestConfig=RequestConfig.custom().setConnectTimeout(1000)
                .setConnectionRequestTimeout(1000).setSocketTimeout(1000).setRedirectsEnabled(true).build();
        HttpGet httpGet=new HttpGet(url);
        httpGet.setConfig(requestConfig);
        Map<String,Object> resultmap=new HashMap<String, Object>();

        String srtResult =null;
        HttpResponse httpResponse=null;
        int StatusCode=404;
        try {


            //System.out.println("httpclient"+url+"请求开始"+System.currentTimeMillis()/1000);
             httpResponse=httpCilent.execute(httpGet);
         //   System.out.println("httpclient"+url+"请求结束"+System.currentTimeMillis()/1000);
        StatusCode=httpResponse.getStatusLine().getStatusCode();
        httpCilent.close();
           return httpResponse;
        } catch (IOException e) {
            httpCilent.close();
        }
      return httpResponse;
    }
    public static HttpResponse dopost(String url) throws IOException {
        CloseableHttpClient httpCilent = HttpClients.custom().build();
        RequestConfig requestConfig = RequestConfig.custom().setConnectTimeout(1000).setConnectionRequestTimeout(1000)
                .setSocketTimeout(1000).setRedirectsEnabled(true).build();
        HttpPost httpPost = new HttpPost(url);
        // 设置超时时间
        httpPost.setConfig(requestConfig);
        Map<String,Object> resultmap=new HashMap<String, Object>();
        String strResult = "";
        int StatusCode=404;
            List<NameValuePair> postData=new ArrayList<>();

            //NameValuePair[] =new NameValuePair[bp.size()];
            int i=0;
        HttpResponse httpResponse=null;
        UrlEncodedFormEntity entity = null;
        try {
            entity = new UrlEncodedFormEntity(postData, "UTF-8");
            httpPost.setEntity(entity);
            long begintime=System.currentTimeMillis();
           httpResponse = httpCilent.execute(httpPost);
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        } catch (ClientProtocolException e) {
            httpCilent.close();
        } catch (IOException e) {
            httpCilent.close();
        }
        // 设置post求情参数

        return httpResponse;
    }

    public static void main(String[] args) throws IOException {
        System.out.println(doget("http://ali-weather.showapi.com/area-to-weather"));
        System.out.println("");
    }
}
