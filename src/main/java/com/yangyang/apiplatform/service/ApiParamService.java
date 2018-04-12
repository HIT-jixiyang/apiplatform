package com.yangyang.apiplatform.service;


import com.yangyang.apiplatform.mapper.ApiParamMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @Author: ny
 * @Date: Created in 19:25 2018/4/7 0007
 */
@Service
public class ApiParamService {
    @Autowired
    ApiParamMapper apiParamMapper;

    public Object getApiParamById(String apiId){
        return apiParamMapper.getApiParamById(apiId);
    }
}
