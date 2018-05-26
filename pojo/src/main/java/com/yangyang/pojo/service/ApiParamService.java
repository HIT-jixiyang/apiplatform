package com.yangyang.pojo.service;

import com.yangyang.pojo.entity.ApiParam;
import com.yangyang.pojo.mapper.ApiParamMapper;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * @Author: ny
 * @Date: Created in 19:25 2018/4/7 0007
 */
@Service
public class ApiParamService {
    private static final org.slf4j.Logger LOGGER = LoggerFactory.getLogger(ApiParamService.class);
    @Autowired
    ApiParamMapper apiParamMapper;
    @Transactional
    public List<ApiParam> getApiParamListById(String apiID){
        return apiParamMapper.getApiParamListById(apiID);
    }
    //根据Api_id去更新参数，先全部删除然后全部插入
    @Transactional
    public boolean updateParamByApiID(List<ApiParam> apiRequestParamList, String api_id){
        apiParamMapper.deleteApiParamByApiID(api_id);
        for(ApiParam apiRequestParam :apiRequestParamList){
            if(!apiParamMapper.addApiParam(apiRequestParam)){ return false;
            }
        }
        return true;
    }

}
