package com.yangyang.apigateway.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.actuate.metrics.CounterService;
import org.springframework.http.ResponseEntity;
import org.springframework.http.client.SimpleClientHttpRequestFactory;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.Map;
import java.util.Set;

/**
 * @program: apiplatform
 * @description:
 * @author: JiXiYang
 * @create: 2018-05-08 20:53
 **/
@Service
public class ResetMetricsService {
    @Value("${server.port}")
    private Integer port;
    @Autowired
    CounterService counterService;
    public static Map<String,Object> map;

    @Scheduled(fixedRate = 1000)
    public void resetAllSecondSpeedCounter(){
        SimpleClientHttpRequestFactory requestFactory = new SimpleClientHttpRequestFactory();
        requestFactory.setConnectTimeout(3000);// 设置超时
        requestFactory.setReadTimeout(3000);
        RestTemplate restTemplate=new RestTemplate(requestFactory);
        ResponseEntity<Map> metrics=restTemplate.getForEntity("http://localhost:"+port+"/metrics", Map.class);
        this. map=metrics.getBody();
        Set<String> ketset=map.keySet();
        for (String key : ketset){
            if(key.startsWith("counter.speed.second")){
                counterService.reset(key);
            }
        }
    }

    /*@Scheduled(fixedRate = 60000)
    public void resetAllMinuteSpeedCounter(){
        SimpleClientHttpRequestFactory requestFactory = new SimpleClientHttpRequestFactory();
        requestFactory.setConnectTimeout(3000);// 设置超时
        requestFactory.setReadTimeout(3000);
        RestTemplate restTemplate=new RestTemplate(requestFactory);
        ResponseEntity<Map> metrics=restTemplate.getForEntity("http://localhost:10000/metrics", Map.class);
        this.map=metrics.getBody();
        Set<String> ketset=map.keySet();
        for (String key : ketset){
            if(key.startsWith("counter.speed.minute")){
                counterService.reset(key);
            }
        }
    }*/
}
