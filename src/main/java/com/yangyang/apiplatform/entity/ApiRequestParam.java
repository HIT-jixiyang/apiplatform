package com.yangyang.apiplatform.entity;

public class ApiRequestParam
{
    String api_id;

    String api_param;
    String api_param_demo;

    @Override
    public String toString() {
        return "ApiRequestParam{" +
                "api_id='" + api_id + '\'' +
                ", api_param='" + api_param + '\'' +
                ", api_param_demo='" + api_param_demo + '\'' +
                '}';
    }

    public String getApi_id() {
        return api_id;
    }

    public void setApi_id(String api_id) {
        this.api_id = api_id;
    }

    public String getApi_param() {
        return api_param;
    }

    public void setApi_param(String api_param) {
        this.api_param = api_param;
    }

    public String getApi_param_demo() {
        return api_param_demo;
    }

    public void setApi_param_demo(String api_param_demo) {
        this.api_param_demo = api_param_demo;
    }


}
