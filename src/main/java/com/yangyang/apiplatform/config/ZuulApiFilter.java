package com.yangyang.apiplatform.config;

import com.netflix.zuul.ZuulFilter;
import com.netflix.zuul.context.RequestContext;
import com.yangyang.apiplatform.entity.Api;
import com.yangyang.apiplatform.entity.ApiAuthorization;
import com.yangyang.apiplatform.entity.App;
import com.yangyang.apiplatform.mapper.ApiMapper;
import com.yangyang.apiplatform.mapper.AppMapper;
import com.yangyang.apiplatform.service.ApiService;
import com.yangyang.apiplatform.service.AppService;
import com.yangyang.apiplatform.service.AuthorizationService;
import com.yangyang.apiplatform.utils.GateWayToken;
import com.yangyang.apiplatform.utils.MD5;
import org.apache.tomcat.util.http.parser.Authorization;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletRequest;
import java.math.BigInteger;

/**
 * @program: apiplatform
 * @description: 自定义过滤器
 * @author: Mr.Wang
 * @create: 2018-04-14 20:59
 **/
@Component
public class ZuulApiFilter extends ZuulFilter {
    @Autowired
    ApiService apiService;
    @Autowired
    AppService appService;
    @Autowired
    AuthorizationService authorizationService;
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
        System.out.println("执行到过滤器");
        RequestContext ctx = RequestContext.getCurrentContext();
        HttpServletRequest request = ctx.getRequest();
        String APPID=request.getHeader("APPID");
       Long timeMill=new Long(request.getHeader("TIMEMILL"));
       String gateWayToken=request.getHeader("GW-TOKEN");
        if (request.getHeader("APPID")==null){
            System.out.println("请求中没有发现App信息");
            ctx.setSendZuulResponse(false);// 过滤该请求，不对其进行路由
            ctx.setResponseStatusCode(401);// 返回错误码
            ctx.setResponseBody("{\"result\":\"缺少App认证信息!\"}");// 返回错误内容
            ctx.set("isSuccess", false);
            return null;
        }else{
            System.out.println(request.getServletPath());
            String api_id=request.getServletPath().substring(1);
            String app_secret=appService.getAppSecretByApp_id(APPID);
            System.out.println(api_id);
            ApiAuthorization apiAuthorization=authorizationService.getAuthorizationByApi_idAndAppID(api_id,APPID);
            if (apiAuthorization!=null){
                if (GateWayToken.getGateWayToken(apiAuthorization.getApp_id(),app_secret,timeMill).equals(gateWayToken)){
                ctx.setSendZuulResponse(true);
                    System.out.println("记账一笔");
                }
            }

        }
        System.out.println("网关发现请求，APPID为"+request.getHeader("APPID"));

        return null;
    }
}
