package com.yangyang.apiplatform.entity;

import java.io.Serializable;

public class ApiParam implements Serializable
{
    String api_param_id;
    String api_id;
    String api_pre_param_key;
    String api_pre_param_value;
    String api_after_param_key;
    String api_after_param_value;
    Integer api_pre_param_position;
    Integer api_after_param_position;
    Boolean api_param_ismust;
    Boolean api_param_isconstant;

    @Override
    public String toString() {
        return "ApiParam{" +
                "api_param_id='" + api_param_id + '\'' +
                ", api_id='" + api_id + '\'' +
                ", api_pre_param_key='" + api_pre_param_key + '\'' +
                ", api_pre_param_value='" + api_pre_param_value + '\'' +
                ", api_after_param_key='" + api_after_param_key + '\'' +
                ", api_after_param_value='" + api_after_param_value + '\'' +
                ", api_pre_param_position=" + api_pre_param_position +
                ", api_after_param_position=" + api_after_param_position +
                ", api_param_ismust=" + api_param_ismust +
                ", api_param_isconstant=" + api_param_isconstant +
                '}';
    }

    public String getApi_param_id() {
        return api_param_id;
    }

    public void setApi_param_id(String api_param_id) {
        this.api_param_id = api_param_id;
    }

    public String getApi_id() {
        return api_id;
    }

    public void setApi_id(String api_id) {
        this.api_id = api_id;
    }

    public String getApi_pre_param_key() {
        return api_pre_param_key;
    }

    public void setApi_pre_param_key(String api_pre_param_key) {
        this.api_pre_param_key = api_pre_param_key;
    }

    public String getApi_pre_param_value() {
        return api_pre_param_value;
    }

    public void setApi_pre_param_value(String api_pre_param_value) {
        this.api_pre_param_value = api_pre_param_value;
    }

    public String getApi_after_param_key() {
        return api_after_param_key;
    }

    public void setApi_after_param_key(String api_after_param_key) {
        this.api_after_param_key = api_after_param_key;
    }

    public String getApi_after_param_value() {
        return api_after_param_value;
    }

    public void setApi_after_param_value(String api_after_param_value) {
        this.api_after_param_value = api_after_param_value;
    }

    public Integer getApi_pre_param_position() {
        return api_pre_param_position;
    }

    public void setApi_pre_param_position(Integer api_pre_param_position) {
        this.api_pre_param_position = api_pre_param_position;
    }

    public Integer getApi_after_param_position() {
        return api_after_param_position;
    }

    public void setApi_after_param_position(Integer api_after_param_position) {
        this.api_after_param_position = api_after_param_position;
    }

    public Boolean getApi_param_ismust() {
        return api_param_ismust;
    }

    public void setApi_param_ismust(Boolean api_param_ismust) {
        this.api_param_ismust = api_param_ismust;
    }

    public Boolean getApi_param_isconstant() {
        return api_param_isconstant;
    }

    public void setApi_param_isconstant(Boolean api_param_isconstant) {
        this.api_param_isconstant = api_param_isconstant;
    }
}
