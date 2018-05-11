package com.yangyang.monitor.service;

import com.yangyang.pojo.entity.Api;
import com.yangyang.pojo.mapper.ApiMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class HeartBeatService {
    @Autowired
    ApiMapper apiMapper;
    public List<Api> GetApiHeartList(){
        return apiMapper.getApiHeartList();
    }
    public void UpdateStatement(String api_id,Integer api_enabled){
        apiMapper.updatestatement(api_id,api_enabled);
    }

}
