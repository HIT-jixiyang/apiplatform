package com.yangyang.apigateway.web;


import com.yangyang.apigateway.service.PerMinuteCountService;
import com.yangyang.apigateway.service.PerSecondCountService;
import com.yangyang.apigateway.service.ResetMetricsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

/**
 * @program: apiplatform
 * @description:
 * @author: JiXiYang
 * @create: 2018-05-08 20:52
 **/
@RestController
public class DataProviderController {
@Autowired
PerSecondCountService perSecondCountService;
@Autowired
    PerMinuteCountService perMinuteCountService;
//访问每秒钟的请求次数使用speed.second开头的计数器
@GetMapping(value = "/monitor/getPerSecondRequestTimes")
    public String getApiRequestPerSecondTimesPerSecond(@RequestParam(value = "counter_name") String counter_name){
       Integer count= (Integer) ResetMetricsService.map.get(counter_name);
       if (count==null){
           return "0";
       }
        return String.valueOf(count);

}
/*//获得每分钟的访问速率，用speed.minute.开头的计数器
    @RequestMapping(value = "/monitor/getPerSecondRequestTimes")
    public String getApiRequestPerSecondTimesPerMinute(@RequestParam(value = "counter_name") String counter_name){
    return String.valueOf((Integer) ResetMetricsService.map.get(counter_name));
}*/
}
