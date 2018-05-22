package com.yangyang.pojo.service;

import com.yangyang.pojo.entity.Consumer;
import com.yangyang.pojo.mapper.ConsumerMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;

@Service
public class ConsumerService {
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
}
