package com.yangyang.pojo.entity;

import lombok.Getter;
import lombok.ToString;

@Getter
@ToString
public enum ContentType {
    JSON(0,"application/json"),
    XML(1,"text/xml"),
    FORMDATA(2," multipart/form-data"),
    FORM_URL_ENCODED(3,"application/x-www-form-urlencoded");
    String type;
    Integer code;
     ContentType(Integer code,String type){
        this.type=type;
        this.code=code;
    }
}
