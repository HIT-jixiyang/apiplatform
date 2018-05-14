package com.yangyang.pojo.entity;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.util.Date;

/**
 * @program: apiplatform
 * @description: API授权信息
 * @author: JiXiYang
 * @create: 2018-04-19 16:49
 **/
@Getter
@Setter
@ToString
public class ApiAuthorization {
    String api_category_id;
    String app_id;
    Date create_date;
    Boolean enabled;
}
