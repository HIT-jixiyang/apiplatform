package com.yangyang.apiplatform.service;


import com.yangyang.apiplatform.entity.ApiRequestParam;
import com.yangyang.apiplatform.mapper.ApiParamMapper;
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
    @Autowired
    ApiParamMapper apiParamMapper;
    @Transactional
    public Object getApiParamListById(String apiID){
        return apiParamMapper.getApiParamListById(apiID);
    }
    //根据Api_id去更新参数，先全部删除然后全部插入
    @Transactional
    public boolean updateParamByApiID(List<ApiRequestParam> apiRequestParamList,String api_id){
        apiParamMapper.deleteApiParamByApiID(api_id);
        for(ApiRequestParam apiRequestParam :apiRequestParamList){
            if(!apiParamMapper.addApiParam(apiRequestParam)){ return false;
            }
        }
        return true;
    }

}
