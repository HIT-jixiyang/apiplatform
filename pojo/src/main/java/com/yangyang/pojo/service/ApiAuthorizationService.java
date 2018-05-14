package com.yangyang.pojo.service;

import com.yangyang.pojo.entity.Api;
import com.yangyang.pojo.entity.ApiAuthorization;
import com.yangyang.pojo.mapper.ApiAuthorizationMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.HashMap;
import java.util.Map;

/**
 * @program: apiplatform
 * @description: 授权服务
 * @author: JiXiYang
 * @create: 2018-04-19 17:08
 **/
@Service
public class ApiAuthorizationService {
    @Autowired
    ApiAuthorizationMapper apiAuthorizationMapper;
    @Autowired
    ApiService apiService;
    @Autowired
    AppService appService;
    @Transactional
    public boolean addApiAuthorization(ApiAuthorization apiAuthorization){
        if(existAuthorization(apiAuthorization.getApi_category_id(),apiAuthorization.getApp_id())){
            System.out.println("授权信息已经存在，不需要再次授权");
            return false;
        }
        else {
           if( apiAuthorizationMapper.addAuthorization(apiAuthorization)==1){
               return true;
           }
           else {
               return false;
           }
        }
    }

    public boolean existAuthorization(String api_category_id, String app_id){
        ApiAuthorization temp=apiAuthorizationMapper.getApiAuthorizationByAppIDAndApiCategoryID(api_category_id,app_id);
        if(temp!=null){
            return true;
        }else {
            return false;
        }
    }
    public void deleteAuthorizationByApiCategoryIDAndAppID(String api_category_id, String app_id){
        apiAuthorizationMapper.deleteApiAuthorizationByAppIDAndApiCategoryID(api_category_id,app_id);
    }
    public void deleteAuthorizationByAppID(String app_id){
        apiAuthorizationMapper.deleteApiAuthorizationByAppIDAndApiID(app_id);
    }

    public void deleteAuthorizationByApiCategoryID(String api_category_id){
        apiAuthorizationMapper.deleteApiAuthorizationByApiCategoryID(api_category_id);
    }
    public ApiAuthorization getAuthorizationByApi_idAndAppID(String api_id, String app_id){
        return apiAuthorizationMapper.getApiAuthorizationByAppIDAndApiCategoryID(api_id,app_id);
    }
    public Map<String, Object> getApiCategoryPageListByApp_id(Integer pageNo, Integer pageSize, String app_id) {
        Map<String, Object> result = new HashMap<>();
        result.put("data", apiAuthorizationMapper.getApiCategoryListByAppID(pageNo,pageSize,app_id));
        result.put("total", apiAuthorizationMapper.getApiCategoryCount(app_id));
        return result;
    }
}
