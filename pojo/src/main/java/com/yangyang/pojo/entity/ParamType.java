package com.yangyang.pojo.entity;

/**
 * @program: apiplatform
 * @description:
 * @author: JiXiYang
 * @create: 2018-05-15 10:12
 **/
public enum ParamType {
    String("String"),
    Integer("Integer"),
    Float("Float"),
    Double("Double"),
    Date("Date");
String paramType;

    public java.lang.String getParamType() {
        return paramType;
    }

    public void setParamType(java.lang.String paramType) {
        this.paramType = paramType;
    }

    ParamType(java.lang.String paramType) {
        this.paramType = paramType;
    }
}
