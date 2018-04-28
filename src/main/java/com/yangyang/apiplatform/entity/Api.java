package com.yangyang.apiplatform.entity;

import java.io.Serializable;
import java.util.List;

public class Api implements Serializable{
    
    String api_id;
    String api_url;
    String sp_id;
    Integer api_max_in;
    Integer api_enabled;
    String api_description;
    String api_path;
    Integer api_bill_type;
    Float api_sys_price;
    Integer api_method;
    String api_name;
String api_return_pattern;
String api_normal_return_demo;
String api_error_return_demo;
Integer api_timeout;

    @Override
    public String toString() {
        return "Api{" +
                "api_id='" + api_id + '\'' +
                ", api_url='" + api_url + '\'' +
                ", sp_id='" + sp_id + '\'' +
                ", api_max_in=" + api_max_in +
                ", api_enabled=" + api_enabled +
                ", api_description='" + api_description + '\'' +
                ", api_path='" + api_path + '\'' +
                ", api_bill_type=" + api_bill_type +
                ", api_sys_price=" + api_sys_price +
                ", api_method=" + api_method +
                ", api_name='" + api_name + '\'' +
                ", api_return_pattern='" + api_return_pattern + '\'' +
                ", api_normal_return_demo='" + api_normal_return_demo + '\'' +
                ", api_error_return_demo='" + api_error_return_demo + '\'' +
                ", api_timeout=" + api_timeout +
                '}';
    }

    public String getApi_id() {
        return api_id;
    }

    public void setApi_id(String api_id) {
        this.api_id = api_id;
    }

    public String getApi_url() {
        return api_url;
    }

    public void setApi_url(String api_url) {
        this.api_url = api_url;
    }

    public String getSp_id() {
        return sp_id;
    }

    public void setSp_id(String sp_id) {
        this.sp_id = sp_id;
    }

    public Integer getApi_max_in() {
        return api_max_in;
    }

    public void setApi_max_in(Integer api_max_in) {
        this.api_max_in = api_max_in;
    }

    public Integer getApi_enabled() {
        return api_enabled;
    }

    public void setApi_enabled(Integer api_enabled) {
        this.api_enabled = api_enabled;
    }

    public String getApi_description() {
        return api_description;
    }

    public void setApi_description(String api_description) {
        this.api_description = api_description;
    }

    public String getApi_path() {
        return api_path;
    }

    public void setApi_path(String api_path) {
        this.api_path = api_path;
    }

    public Integer getApi_bill_type() {
        return api_bill_type;
    }

    public void setApi_bill_type(Integer api_bill_type) {
        this.api_bill_type = api_bill_type;
    }

    public Float getApi_sys_price() {
        return api_sys_price;
    }

    public void setApi_sys_price(Float api_sys_price) {
        this.api_sys_price = api_sys_price;
    }

    public Integer getApi_method() {
        return api_method;
    }

    public void setApi_method(Integer api_method) {
        this.api_method = api_method;
    }

    public String getApi_name() {
        return api_name;
    }

    public void setApi_name(String api_name) {
        this.api_name = api_name;
    }

    public String getApi_return_pattern() {
        return api_return_pattern;
    }

    public void setApi_return_pattern(String api_return_pattern) {
        this.api_return_pattern = api_return_pattern;
    }

    public String getApi_normal_return_demo() {
        return api_normal_return_demo;
    }

    public void setApi_normal_return_demo(String api_normal_return_demo) {
        this.api_normal_return_demo = api_normal_return_demo;
    }

    public String getApi_error_return_demo() {
        return api_error_return_demo;
    }

    public void setApi_error_return_demo(String api_error_return_demo) {
        this.api_error_return_demo = api_error_return_demo;
    }

    public Integer getApi_timeout() {
        return api_timeout;
    }

    public void setApi_timeout(Integer api_timeout) {
        this.api_timeout = api_timeout;
    }
}
