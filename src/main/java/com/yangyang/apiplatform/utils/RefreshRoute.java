package com.yangyang.apiplatform.utils;

import com.yangyang.apiplatform.event.RefreshRouteService;
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
