/*
package com.yangyang.monitor.web;

import com.yangyang.monitor.service.PerSecondCountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

*/
/**
 * @program: apiplatform
 * @description:
 * @author: JiXiYang
 * @create: 2018-05-08 20:52
 **//*

@CrossOrigin
@RestController
public class DataProviderController {
@Autowired
PerSecondCountService perSecondCountService;
//访问每秒钟的请求次数使用speed.second开头的计数器
@RequestMapping(value = "/monitor/get_api_request_times_per_second")
    public Integer getApiRequestPerSecondTimesPerSecond(@PathVariable(value = "counter_name") String counter_name){
        return  perSecondCountService.getRequestSpeedByCounterName(counter_name);
}
//获得每分钟的访问速率，用speed.minute.开头的计数器
@RequestMapping(value = "/monitor/get_api_request_times_per_minute")
    public Integer getApiRequestPerSecondTimesPerMinute(@PathVariable(value = "counter_name") String counter_name){
        return  perSecondCountService.getRequestSpeedByCounterName(counter_name);
}
}
*/
