package com.yangyang.apiplatform.service;

import com.yangyang.apiplatform.entity.Api;
import com.yangyang.apiplatform.entity.ApiRequestParam;
import com.yangyang.apiplatform.mapper.ApiMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class ApiService {
    @Autowired
    ApiMapper apiMapper;

    public boolean addApi(Api api) {
        boolean flag = false;
        if (!apiMapper.addApi(
                api.getApi_id(),
                api.getApi_url(),
                api.getSp_id(),
                api.getApi_token(),
                api.getApi_max_in(),
                api.getApi_enabled(),
                api.getApi_description(),
                api.getApi_strip_prefix(),
                api.getApi_retryable(),
                api.getApi_path(),
                api.getApi_name(),
                api.getApi_bill_type(),
                api.getApi_sys_price(),
                api.getApi_method(),
                api.getApi_return_pattern(),
                api.getApi_demo(),
                api.getApi_normal_return_demo(),
                api.getApi_error_return_demo()
        )) return false;
        List<ApiRequestParam> apiRequestParamsList = api.getApiRequestParamList();
        for (ApiRequestParam p : apiRequestParamsList) {
            p.setApi_id(api.getApi_id());
            if (!apiMapper.addApiParam(p)) return false;
        }
        return true;

    }

    public List<Api> getApiListBySpId(String sp_id) {
        return apiMapper.getApiListBySpId(sp_id);
    }

    public boolean updateApi(Api api) {
        return apiMapper.updateApi(api);
    }

    public List<Api> getAllApi() {
        return apiMapper.getAllApiList();
    }

    public Object getApiPageList(Integer pageNo, Integer pageSize, Api api) {
        Map<String, Object> result = new HashMap<>();
        result.put("data", apiMapper.getApiPageList(pageNo, pageSize, api));
        result.put("total", apiMapper.countPageList(api));
        return result;
    }
}
