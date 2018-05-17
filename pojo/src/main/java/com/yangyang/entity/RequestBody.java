package com.yangyang.entity;


import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.springframework.util.MultiValueMap;

import java.io.Serializable;
import java.util.Map;

/**
 * @program: parammap
 * @description:请求体定义
 * @author: JiXiYang
 * @create: 2018-05-13 16:53
 **/
@Setter
@Getter
@ToString
public class RequestBody implements Serializable {
    String param_type;/*
                        application/x-www-form-urlencoded
                       multipart/form-data
                        application/json
                        text/xml*/
    MultiValueMap<String, String> header;//请求头中的参数
    MultiValueMap<String, String> query;//请求路径中的参数
    Object body;//请求体中的参数
}
