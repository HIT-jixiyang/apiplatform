package com.yangyang.apigateway.web;

import com.yangyang.apigateway.event.RefreshRouteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.netflix.zuul.web.ZuulHandlerMapping;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

/**
 * Created by xujingfeng on 2017/4/1.
 */
@CrossOrigin
@RestController
public class ZuulController {

    @Autowired
    RefreshRouteService refreshRouteService;

    @RequestMapping("/refreshRoute")
    public String refreshRoute(){
        refreshRouteService.refreshRoute();
        return "refreshRoute";
    }

    @Autowired
    ZuulHandlerMapping zuulHandlerMapping;

    @RequestMapping("/watchNowRoute")
    public String watchNowRoute(){
        //可以用debug模式看里面具体是什么
        Map<String, Object> handlerMap = zuulHandlerMapping.getHandlerMap();
        return "watchNowRoute";
    }



}
