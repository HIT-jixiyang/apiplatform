package com.yangyang.pojo.service;


import com.yangyang.pojo.entity.App;
import com.yangyang.pojo.mapper.ApiMapper;
import com.yangyang.pojo.mapper.AppMapper;
import com.yangyang.utils.utils.AppID;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @program: apiplatform
 * @description: App的服务
 * @author: JiXiYang
 * @create: 2018-04-15 21:34
 **/
@Service
public class AppService {
    @Autowired
    AppMapper appMapper;
    @Autowired
    ApiMapper apiMapper;
@Autowired
AuthorizationService authorizationService;
    public boolean addApp(App app) {
        app.setApp_id(AppID.getAppID());
        if (appMapper.addApp(app) == 1) {
            return true;
        } else {
            return false;
        }
    }

    public List<App> getAppListByConsumerID(String ConsumerID) {
        return appMapper.getAppListByConsumerID(ConsumerID);
    }

    public App findAppByAppID(String app_id) {
        return appMapper.getAppByAppID(app_id);
    }

    public Map<String, Object> getAppPageList(Integer pageNo, Integer pageSize, App app) {
        Map<String, Object> result = new HashMap<>();
        result.put("data", appMapper.getAppPageList(pageNo, pageSize, app));
        result.put("total", appMapper.countPageList(app));
        return result;
    }

    /*   public Consumer findConsumerByApp_id(BigInteger app_id){
           App app=findAppByAppID(app_id);
           Consumer consumer=
       }*/
    @Transactional
    public String getAppSecretByApp_id(String app_id) {
        App app = appMapper.getAppByAppID(app_id);
        if (app != null) {
            return app.getApp_secret();
        } else return "";
    }

    @Transactional
    public boolean deleteByApp_id(String app_id) {
        App app = appMapper.getAppByAppID(app_id);
        if (app != null) {
            authorizationService.deleteAuthorizationByAppID(app_id);
            if (appMapper.deleteAppByAppID(app_id)==1){
                return true;
            }
            else return false;
        }
        else return false;
    }
    public boolean updateAppByAppID(App app){
        if(appMapper.updateAppByAppID(app.getApp_name(),app.getApp_description(),app.getApp_id())==1){
            return true;
        }
        else {
            return false;
        }
    }
}
