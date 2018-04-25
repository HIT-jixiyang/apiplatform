package com.yangyang.apiplatform.entity;

import java.math.BigInteger;
import java.util.Date;

/**
 * @program: apiplatform
 * @description: API授权信息
 * @author: JiXiYang
 * @create: 2018-04-19 16:49
 **/
public class ApiAuthorization {
    String api_id;
    String app_id;
    Date create_date;

    @Override
    public String toString() {
        return "ApiAuthorization{" +
                "api_id='" + api_id + '\'' +
                ", app_id='" + app_id + '\'' +
                ", create_date=" + create_date +
                '}';
    }

    public String getApi_id() {
        return api_id;
    }

    public void setApi_id(String api_id) {
        this.api_id = api_id;
    }

    public String getApp_id() {
        return app_id;
    }

    public void setApp_id(String app_id) {
        this.app_id = app_id;
    }

    public Date getCreate_date() {
        return create_date;
    }

    public void setCreate_date(Date create_date) {
        this.create_date = create_date;
    }
}
