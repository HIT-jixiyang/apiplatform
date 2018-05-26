package com.yangyang.pojo.service;

import com.yangyang.pojo.entity.Consumer;
import com.yangyang.pojo.mapper.ConsumerMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;


@Service
public class ConsumerService {
    public final static Logger LOGGER=  LoggerFactory.getLogger(ConsumerService.class);
    @Autowired
    ConsumerMapper consumerMapper;

    public Boolean addConsumer(Consumer consumer) {
        if (consumerMapper.addConsumer(consumer) == 1) {
            return true;
        } else {
            return false;
        }
    }

    public Boolean updateConsumer(Consumer consumer) {
        if (consumerMapper.updateConsumerByConsumerExample(consumer) == 1) {
            return true;
        } else {
            return false;
        }
    }

    public Map<String, Object> getConsumerList(Integer pageNo, Integer pageSize, Consumer consumer, String key) {
        Map<String, Object> map = new HashMap<>();
        map.put("total", consumerMapper.getCountList(consumer, key));
        map.put("data", consumerMapper.getConsumerPageListByConsumerExample(pageNo, pageSize, consumer, key));
        return map;
    }

    public Consumer ConsumerLogin(String email,String password){
        try {
            Consumer consumer=consumerMapper.getConsumerByEmail(email);
            if(consumer==null){
                return  null;
            }
            if(!consumer.getConsumer_password().equals(password)){
                return null;
            }
            return  consumer;
        }catch (Exception e){
            LOGGER.error(e.getMessage());
            return null;
        }

    }
}
