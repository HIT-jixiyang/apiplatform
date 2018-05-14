package com.yangyang.apigateway.service;

import com.yangyang.pojo.entity.BillItem;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.http.client.SimpleClientHttpRequestFactory;
import org.springframework.scheduling.annotation.Async;
import org.springframework.scheduling.annotation.AsyncResult;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.Future;

/**
 * @program: apiplatform
 * @description: 记账请求异步任务
 * @author: JiXiYang
 * @create: 2018-05-09 16:01
 **/
@Service
public class BillService {
    private static final org.slf4j.Logger LOGGER = LoggerFactory.getLogger(BillService.class);
    @Async
    public Future<Object> bill(String bill_item_id,String api_id, String app_id){
        LOGGER.info("检测到记账请求");
        SimpleClientHttpRequestFactory requestFactory = new SimpleClientHttpRequestFactory();
        requestFactory.setConnectTimeout(1000);// 设置超时
        requestFactory.setReadTimeout(1000);
        RestTemplate restTemplate=new RestTemplate(requestFactory);
        Map<String,Object> map=new HashMap<>();
    /*      BillItem billItem=new BillItem();
          billItem.setBill_item_id(bill_item_id);
          billItem.setApp_id(app_id);
          billItem.setApi_id(api_id);*/
          map.put("bill_item_id",bill_item_id);
          map.put("api_id",api_id);
          map.put("app_id",app_id);

        ResponseEntity<Map> responseEntity= restTemplate.postForEntity("http://127.0.0.1:10002/bill/addbill", map,Map.class);
         Map resultMap=responseEntity.getBody();
         Future<Object> future= new AsyncResult<>(resultMap);
         return future;
    }
    @Async
    public Future<Object> updateBill(BillItem billItem){
        SimpleClientHttpRequestFactory requestFactory = new SimpleClientHttpRequestFactory();
        requestFactory.setConnectTimeout(1000);// 设置超时
        requestFactory.setReadTimeout(1000);
        RestTemplate restTemplate=new RestTemplate(requestFactory);
        Map<String,Object> map=new HashMap<>();
        map.put("bill_item_id",billItem.getBill_item_id());
        map.put("request_time",billItem.getRequest_time());
        map.put("response_code",billItem.getResponse_code());
        ResponseEntity<Map> responseEntity= restTemplate.postForEntity("http://127.0.0.1:10002/bill/updatebill", (Object) map,Map.class);
        Map resultMap=responseEntity.getBody();
        Future<Object> future= new AsyncResult<>(resultMap);
        return future;
    }
}
