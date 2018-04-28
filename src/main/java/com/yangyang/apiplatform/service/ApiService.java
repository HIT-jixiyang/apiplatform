package com.yangyang.apiplatform.service;

import com.yangyang.apiplatform.entity.Api;
import com.yangyang.apiplatform.entity.ApiParam;
import com.yangyang.apiplatform.mapper.ApiMapper;
import com.yangyang.apiplatform.mapper.ApiParamMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class ApiService {
    @Autowired
    ApiMapper apiMapper;
    @Autowired
    ApiParamMapper apiParamMapper;

    //插入API同时插入参数列表
    @Transactional
    public boolean addApi(Api api,List<ApiParam> apiParamList) {
        boolean flag = false;
        if (!apiMapper.addApi(api)) return false;

        for (ApiParam p : apiParamList) {
            p.setApi_id(api.getApi_id());
            if (!apiParamMapper.addApiParam(p)) return false;
        }
        return true;

    }

    public List<Api> getApiListBySpId(String sp_id) {
        return apiMapper.getApiListBySpId(sp_id);
    }

    public boolean updateApi(Api api) {
        return apiMapper.updateApiByApiID(api);
    }

    public List<Api> getAllApi() {
        return apiMapper.getAllApiList();
    }

    public Object getApiPageList(Integer pageNo, Integer pageSize, Api api) {
        Map<String, Object> result = new HashMap<>();
        result.put("data", apiMapper.getApiPageListByApiExample(pageNo, pageSize, api));
        result.put("total", apiMapper.countPageList(api));
        return result;
    }

    @Transactional
    public void deleteApiByApiID(String api_id) {
        apiMapper.deleteApiByApiID(api_id);
        apiParamMapper.deleteApiParamByApiID(api_id);
    }
    public Api getApiByApiID(String api_id){
         return  apiMapper.getApiByApiID(api_id);
    }

}
