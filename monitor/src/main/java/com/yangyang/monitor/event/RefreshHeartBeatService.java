package com.yangyang.monitor.event;

import com.yangyang.monitor.service.HeartBeatService;
import com.yangyang.pojo.entity.Api;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class RefreshHeartBeatService {
    @Autowired
    HeartBeatService hearBeatService;
    @Autowired
    requestweb requestweb;
    @Async("myTaskAsyncPool")
    public void RefreshHeartBearService() {
        int N;
        int MAX;
        List<Api> ApiList = hearBeatService.GetApiHeartList();
        MAX = ApiList.size();
        System.out.println("一共"+MAX+"条API数据");
        for (N = 0; N <MAX; N++)
        {
            System.out.println(N);
            Api api = ApiList.get(N);
            requestweb.requestweb(api);

        }



        }
    }

