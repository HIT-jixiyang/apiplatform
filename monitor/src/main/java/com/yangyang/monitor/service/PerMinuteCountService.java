package com.yangyang.monitor.service;

import org.springframework.http.ResponseEntity;
import org.springframework.http.client.SimpleClientHttpRequestFactory;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.Map;

/**
 * @program: apiplatform
 * @description:
 * @author: JiXiYang
 * @create: 2018-05-08 20:57
 **/
@Service
public class PerMinuteCountService {
    public Integer getRequestSpeedByCounterName(String countername){
        SimpleClientHttpRequestFactory requestFactory = new SimpleClientHttpRequestFactory();
        requestFactory.setConnectTimeout(1000);// 设置超时
        requestFactory.setReadTimeout(1000);
        RestTemplate restTemplate=new RestTemplate(requestFactory);
        ResponseEntity<Map> metrics=restTemplate.getForEntity("http://localhost:8080/metrics", Map.class);
        Map<String,Object> map=metrics.getBody();
        if (!countername.startsWith("speed.minute")){
            return new Integer(0);
        }
        Integer count = (Integer) map.get("counter."+countername);
        if(count==null){
            count=new Integer(0);
        }
        //System.out.println(map.get("counter."+countername)+"");
        return count;
    }

}
