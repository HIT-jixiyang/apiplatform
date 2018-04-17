package com.yangyang.apiplatform.entity;

import org.springframework.web.servlet.config.annotation.InterceptorRegistry;

import java.math.BigInteger;

/**
 * @program: apiplatform
 * @description: yingyong
 * @author: JiXiYang
 * @create: 2018-04-15 17:16
 **/

public class App {

    BigInteger app_id;
    String app_secret;
    String app_description;
    String app_name;
String consumer_id;

    @Override
    public String toString() {
        return "App{" +
                "app_id=" + app_id +
                ", app_secret='" + app_secret + '\'' +
                ", app_description='" + app_description + '\'' +
                ", app_name='" + app_name + '\'' +
                ", consumer_id='" + consumer_id + '\'' +
                '}';
    }

    public BigInteger getApp_id() {
        return app_id;
    }

    public void setApp_id(BigInteger app_id) {
        this.app_id = app_id;
    }

    public String getApp_secret() {
        return app_secret;
    }

    public void setApp_secret(String app_secret) {
        this.app_secret = app_secret;
    }

    public String getApp_description() {
        return app_description;
    }

    public void setApp_description(String app_description) {
        this.app_description = app_description;
    }

    public String getApp_name() {
        return app_name;
    }

    public void setApp_name(String app_name) {
        this.app_name = app_name;
    }

    public String getConsumer_id() {
        return consumer_id;
    }

    public void setConsumer_id(String consumer_id) {
        this.consumer_id = consumer_id;
    }
}
