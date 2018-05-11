package com.yangyang.apigateway.service;

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
public class PerSecondCountService {
    public Integer getRequestSpeedByCounterName(String countername){
        if (!countername.startsWith("speed.second")){
            return new Integer(0);
        }
       SimpleClientHttpRequestFactory requestFactory = new SimpleClientHttpRequestFactory();
        requestFactory.setConnectTimeout(3000);// 设置超时
        requestFactory.setReadTimeout(3000);
        RestTemplate restTemplate=new RestTemplate(requestFactory);
        ResponseEntity<Map> metrics=restTemplate.getForEntity("http://localhost:10000/metrics", Map.class);
        Map<String,Object> map=metrics.getBody();
        Integer count = (Integer) map.get("counter"+countername);
        if(count==null){
            count=new Integer(0);
        }

        //System.out.println(map.get("counter."+countername)+"");
        return count;
    }

}
