package com.yangyang.apigateway.filter;

import com.netflix.zuul.ZuulFilter;
import com.netflix.zuul.context.RequestContext;
import com.yangyang.apigateway.service.BillService;
import com.yangyang.pojo.service.ApiService;
import com.yangyang.pojo.service.AppService;
import com.yangyang.pojo.service.ApiAuthorizationService;
import com.yangyang.utils.utils.BillItemID;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.actuate.metrics.CounterService;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletRequest;
import java.net.URL;
/*
*
 * @program: apiplatform
 * @description: 自定义过滤器
 * @author: Mr.Wang
 * @create: 2018-04-14 20:59
 **/

@Component
public class ZuulPreFilter extends ZuulFilter {
    private static final org.slf4j.Logger LOGGER = LoggerFactory.getLogger(ZuulPreFilter.class);
    @Autowired
    ApiService apiService;
    @Autowired
    AppService appService;
    @Autowired
    BillService billService;
    @Autowired
    ApiAuthorizationService apiAuthorizationService;
    @Autowired
    CounterService counterService;
    @Override
    public String filterType() {
        return "pre";
    }

    @Override
    public int filterOrder() {
        return 0;
    }

    @Override
    public boolean shouldFilter() {
        return true;
    }

    @Override
    public Object run() {
      //  System.out.println("执行到过滤器");
        RequestContext ctx = RequestContext.getCurrentContext();
        ctx.setSendZuulResponse(true);
       /*if(checkPerssion(ctx)){
           LOGGER.info("转发！");
           ctx.setSendZuulResponse(true);
       }
       else {
           ctx.setSendZuulResponse(false);
           ctx.setResponseStatusCode(401);// 返回错误码
           ctx.setResponseBody("unable");// 返回错误内容
           ctx.set("isSuccess", false);
       }*/
        return null;
    }

    private boolean checkPerssion(RequestContext ctx){
        HttpServletRequest request = ctx.getRequest();
        String APPID=request.getHeader("app_id");
        Long timeMill=new Long(request.getHeader("timestamp"));
        String gateWayToken=request.getHeader("gw_token");
        if (request.getHeader("app_id")==null||request.getHeader("GW-TOKEN")==null){
            System.out.println("请求中没有发现App信息");
            ctx.setResponseStatusCode(401);// 返回错误码
            ctx.setResponseBody("unable");// 返回错误内容
            ctx.set("isSuccess", false);
            return true;
        }else{
            String api_id=request.getServletPath().substring(1);
            String app_secret=appService.getAppSecretByApp_id(APPID);
            Integer timeout=apiService.getApiByApiID(api_id).getApi_timeout();
        LOGGER.info("api_id:"+api_id+" app_id:"+APPID+"  app_secret:"+app_secret+" timeout"+timeout);
/*            ApiAuthorization apiAuthorization=authorizationService.getAuthorizationByApi_idAndAppID(api_id,APPID);
            LOGGER.info(apiAuthorization.toString());
            if (apiAuthorization!=null){
                    setCounter(api_id);
                LOGGER.info(GateWayToken.getGateWayToken(apiAuthorization.getApp_id(),app_secret,timeMill));
                if (GateWayToken.getGateWayToken(apiAuthorization.getApp_id(),app_secret,timeMill).equals(gateWayToken)){
              //  counterService.increment("speed.minute."+api_id);
                    ctx.set("startTime",System.currentTimeMillis());
                    LOGGER.info(APPID+"验证通过");
                    ctx.set("request_timeout",timeout);
                    ctx.set("api_id",api_id);
                    ctx.set("app_id",APPID);
                    LOGGER.info("app_id:"+APPID+"  api_id:"+api_id+"发起记账请求");
                   //记账请求
                    String bill_item_id= BillItemID.getBillItemID();
                    ctx.set("bill_item_id",bill_item_id);
                    billService.bill(bill_item_id,api_id,APPID);
                    return true;
                }else {
                    ctx.setSendZuulResponse(false);
                    LOGGER.info("验证失败");
                    return false;
                }
            }else {


                return false;
            }*/
            counterService.increment("speed.second."+api_id);
            ctx.set("startTime",System.currentTimeMillis());
            LOGGER.info(APPID+"验证通过");
            ctx.set("request_timeout",timeout);
            ctx.set("api_id",api_id);
            ctx.set("app_id",APPID);
            LOGGER.info("app_id:"+APPID+"  api_id:"+api_id+"发起记账请求");
            //记账请求
            String bill_item_id= BillItemID.getBillItemID();
            ctx.set("bill_item_id",bill_item_id);
            billService.bill(bill_item_id,api_id,APPID);
            return true;
        }
    }
    private void setCounter(String api_id){
        counterService.increment("speed.second."+api_id);
        counterService.increment("speed.second.requesttotal");

    }

}
