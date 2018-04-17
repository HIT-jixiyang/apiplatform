package com.yangyang.apiplatform.service;

import com.yangyang.apiplatform.entity.Api;
import com.yangyang.apiplatform.entity.App;
import com.yangyang.apiplatform.mapper.AppMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigInteger;
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

    public boolean addApp(App app) {
        if (appMapper.addApp(app) == 1) {
            return true;
        } else {
            return false;
        }
    }

    public List<App> getAppListByConsumerID(String ConsumerID) {
        return appMapper.getAppListByConsumerID(ConsumerID);
    }

    public App findAppByAppID(BigInteger app_id) {
        return appMapper.getAppByAppID(app_id);
    }

    public Map<String, Object> getAppPageList(Integer pageNo, Integer pageSize, App app) {
        Map<String, Object> result = new HashMap<>();
        result.put("data", appMapper.getAppPageList(pageNo, pageSize, app));
        result.put("total", appMapper.countPageList(app));
        return result;
    }
}
