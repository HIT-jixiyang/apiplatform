package com.yangyang.pojo.entity;

/**
 * @program: apiplatform
 * @description:
 * @author: JiXiYang
 * @create: 2018-05-06 15:45
 **/
public enum  ResultStatusCode {
OK(1,"ok"),
    SYSTEM_ERROR(0,"error"),
LOGIN_ERROR(401,"登录失败");
int statuscode;
String message;

    ResultStatusCode(int statuscode, String message) {
        this.statuscode = statuscode;
        this.message = message;
    }

    public int getStatuscode() {
        return statuscode;
    }

    public void setStatuscode(int statuscode) {
        this.statuscode = statuscode;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
