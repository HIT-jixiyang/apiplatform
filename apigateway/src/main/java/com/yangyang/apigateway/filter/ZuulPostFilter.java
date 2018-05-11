package com.yangyang.apigateway.filter;

import com.netflix.zuul.ZuulFilter;
import com.netflix.zuul.context.RequestContext;
import com.netflix.zuul.http.ServletInputStreamWrapper;
import com.yangyang.apigateway.service.BillService;
import com.yangyang.pojo.entity.BillItem;
import org.apache.http.Consts;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.stereotype.Component;
import org.springframework.util.StreamUtils;

import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.io.InputStream;
import java.nio.charset.Charset;

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

        billUpdate(context);
        InputStream stream = context.getResponseDataStream();
        try {
            String body = StreamUtils.copyToString(stream, Charset.forName("UTF-8"));

         byte[] bytes=body.getBytes(Consts.UTF_8);
        context.setResponseDataStream(new ServletInputStreamWrapper(bytes));
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }
private void billUpdate(RequestContext context){
    HttpServletRequest request = context.getRequest();
    long statrtTime = (long) context.get("startTime");//请求的开始时间
    String api_id= (String) context.get("api_id");
    String app_id= (String) context.get("app_id");
    String bill_item_id= (String) context.get("bill_item_id");
    String response_code= String.valueOf(context.getResponseStatusCode());

    BillItem billItem=new BillItem();
    billItem.setBill_item_id(bill_item_id);
    billItem.setApp_id(app_id);
    billItem.setApi_id(api_id);
    billItem.setResponse_code(response_code);
    long duration=System.currentTimeMillis() - statrtTime;//请求耗时
   LOGGER.info("请求路径"+request.getServletPath()+" --- "+"duration:"+" "+duration);
    Float request_time= new Float(duration/1000.0);
    billItem.setRequest_time(request_time);
    billService.updateBill(billItem);
}
}
