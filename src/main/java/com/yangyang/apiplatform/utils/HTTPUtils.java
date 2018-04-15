package com.yangyang.apiplatform.utils;

import org.apache.commons.lang3.ObjectUtils;
import org.apache.http.HttpEntity;
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

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.nio.charset.Charset;
import java.util.*;

public class HTTPUtils {

    public static Map<String,Object> doget(String url, List<Map> hp){
        CloseableHttpClient httpCilent = HttpClients.custom().build();
        RequestConfig requestConfig=RequestConfig.custom().setConnectTimeout(5000)
                .setConnectionRequestTimeout(5000).setSocketTimeout(5000).setRedirectsEnabled(true).build();
        HttpGet httpGet=new HttpGet(url);
        httpGet.setConfig(requestConfig);
        Map<String,Object> resultmap=new HashMap<String, Object>();
        int n=hp.size();
        for(Map m:hp){
            if(!m.get("key").equals(""))
            httpGet.setHeader((String)m.get("key"),(String) m.get("value"));
        }
        String srtResult =null;
        int StatusCode=404;
        try {
            long begintime=System.currentTimeMillis();


            HttpResponse httpResponse=httpCilent.execute(httpGet);
            long endtime=System.currentTimeMillis();
        StatusCode=httpResponse.getStatusLine().getStatusCode();
            if (httpResponse.getStatusLine().getStatusCode() == 200) {
                srtResult = EntityUtils.toString(httpResponse.getEntity(),"UTF-8");// 获得返回的结果
                resultmap.put("response",srtResult);
                resultmap.put("statuscode","成功");
                resultmap.put("time",(float)(endtime-begintime)/1000);
                return resultmap;
            } else {
                srtResult = EntityUtils.toString(httpResponse.getEntity(),"UTF-8");// 获得返回的结果
                resultmap.put("response",srtResult);
                resultmap.put("statuscode","异常");
                resultmap.put("time",(float)(endtime-begintime)/1000);
                return resultmap;
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        resultmap.put("response","网关出错");
        resultmap.put("statuscode","出错");
        resultmap.put("time",0);
        return resultmap;
    }
    public static Map<String,Object> dopost(String url,List<Map> hp,List<Map> bp){
        CloseableHttpClient httpCilent = HttpClients.custom().build();
        RequestConfig requestConfig = RequestConfig.custom().setConnectTimeout(5000).setConnectionRequestTimeout(5000)
                .setSocketTimeout(5000).setRedirectsEnabled(true).build();
        HttpPost httpPost = new HttpPost(url);
        // 设置超时时间
        httpPost.setConfig(requestConfig);
        Map<String,Object> resultmap=new HashMap<String, Object>();
        String strResult = "";
        int StatusCode=404;
        for(Map m:hp){
            httpPost.setHeader((String)m.get("key"),(String) m.get("value"));
        }
        try {
            List<NameValuePair> postData=new ArrayList<>();

            //NameValuePair[] =new NameValuePair[bp.size()];
            int i=0;
            for(Map map:bp){
                NameValuePair nvp= new NameValuePair() {
                    @Override
                    public String getName() {
                        return (String) map.get("key");
                    }

                    @Override
                    public String getValue() {
                        return (String)map.get("value");
                    }
                };
                postData.add(nvp);
            }

            UrlEncodedFormEntity entity = new UrlEncodedFormEntity(postData, "UTF-8");
            // 设置post求情参数
            httpPost.setEntity(entity);
            long begintime=System.currentTimeMillis();
            HttpResponse httpResponse = httpCilent.execute(httpPost);
            long endtime=System.currentTimeMillis();
            if (httpResponse != null) {
                StatusCode=httpResponse.getStatusLine().getStatusCode();
                if (httpResponse.getStatusLine().getStatusCode() == 200) {
                    strResult = EntityUtils.toString(httpResponse.getEntity());
                  resultmap.put("response",strResult);
                  resultmap.put("statuscode",StatusCode);
                  resultmap.put("time",(float)(endtime-begintime)/1000);
                    return resultmap;
                }  else {
                    strResult = "Error Response: " +EntityUtils.toString(httpResponse.getEntity());
                    resultmap.put("response",strResult);
                    resultmap.put("statuscode",StatusCode);
                    resultmap.put("time",(float)(endtime-begintime)/1000);
                    return resultmap;
                }
            } else {

            }
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        } catch (ClientProtocolException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        resultmap.put("response","网关出错");
        resultmap.put("statuscode","000");
        resultmap.put("time",0);
        return resultmap;
    }
}
