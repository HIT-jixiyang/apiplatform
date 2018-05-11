package com.yangyang.apigateway.utils;


import com.yangyang.apigateway.event.RefreshRouteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

@Component
public class RefreshRoute {
    @Autowired
    RefreshRouteService refreshRouteService;
    @Scheduled(cron = "5 * * * * *")
    public void RefreshRoute(){
        refreshRouteService.refreshRoute();
    }
}
