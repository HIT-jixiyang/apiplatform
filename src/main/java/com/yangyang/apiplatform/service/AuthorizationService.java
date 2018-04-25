package com.yangyang.apiplatform.service;

import com.yangyang.apiplatform.entity.ApiAuthorization;
import com.yangyang.apiplatform.mapper.ApiAuthorizationMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigInteger;

/**
 * @program: apiplatform
 * @description: 授权服务
 * @author: JiXiYang
 * @create: 2018-04-19 17:08
 **/
@Service
public class AuthorizationService {
    @Autowired
    ApiAuthorizationMapper apiAuthorizationMapper;
    @Autowired
    ApiService apiService;
    @Autowired
    AppService appService;
    @Transactional
    public boolean addApiAuthorization(ApiAuthorization apiAuthorization){
        if(existAuthorization(apiAuthorization.getApi_id(),apiAuthorization.getApp_id())){
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

    public boolean existAuthorization(String api_id, String app_id){
        ApiAuthorization temp=apiAuthorizationMapper.getApiAuthorizationByAppIDAndApiID(api_id,app_id);
        if(temp!=null){
            return true;
        }else {
            return false;
        }
    }
    public void deleteAuthorizationByApi_idAndAppID(String api_id, String app_id){
        apiAuthorizationMapper.deleteApiAuthorizationByAppIDAndApiID(api_id,app_id);
    }
    public ApiAuthorization getAuthorizationByApi_idAndAppID(String api_id, String app_id){
        return apiAuthorizationMapper.getApiAuthorizationByAppIDAndApiID(api_id,app_id);
    }
}
