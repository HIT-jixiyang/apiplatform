package com.yangyang.monitor.utils;

import com.yangyang.monitor.event.RefreshHeartBeatService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.io.IOException;

@Component
public class RefreshHeartBeat {
    @Autowired
    RefreshHeartBeatService refreshHeartBeatService;
    @Scheduled(cron = "*/5 * * * * ?")
    public void RefreshHeartBeat() throws IOException {
        System.out.println("开始执行任务");
          refreshHeartBeatService.RefreshHeartBearService();
    }
}
