package com.yangyang.monitor.event;

import com.yangyang.monitor.service.HeartBeatService;
import com.yangyang.monitor.utils.HTTPUtils;
import com.yangyang.pojo.entity.Api;
import org.apache.http.HttpResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Component;

import java.io.IOException;

@Component
public class requestweb {

    @Autowired
    HeartBeatService hearBeatService;
    @Autowired
    HTTPUtils httpUtils;

@Async("myTaskAsyncPool")
public void requestweb(Api api) throws IOException {
  //  System.out.println(api.getApi_url()+"请求开始当前时间"+(System.currentTimeMillis()/1000));

    String api_url = api.getApi_url();
    String api_id = api.getApi_id();
    int api_method= api.getApi_method();
    int api_enabled = api.getApi_enabled();
    if (api_method==0){

        HttpResponse response=httpUtils.doget(api_url);
     //   System.out.println(api.getApi_url()+"请求结束当前时间"+(System.currentTimeMillis()/1000));
        if (response == null) {
            System.out.println(api_url+" 不可用");
            if (api_enabled == 1)
                api_enabled = 0;
            hearBeatService.UpdateStatement(api_id, api_enabled);
        } else {

            System.out.println(api_url+" 可用");
            if (api_enabled == 0)
                api_enabled = 1;
            hearBeatService.UpdateStatement(api_id, api_enabled);
        }

    }
    else {
        HttpResponse response=httpUtils.dopost(api_url);
     //   System.out.println(api.getApi_url()+"请求结束当前时间"+(System.currentTimeMillis()/1000));
        if (response == null) {
            System.out.println(api_url+" 不可用");
            if (api_enabled == 1)
                api_enabled = 0;
            hearBeatService.UpdateStatement(api_id, api_enabled);
        } else {
            System.out.println(api_url+" 可用");
            if (api_enabled == 0)
                api_enabled = 1;
            hearBeatService.UpdateStatement(api_id, api_enabled);
        }

    }
}

}
