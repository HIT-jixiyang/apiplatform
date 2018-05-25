package com.yangyang.pojo.entity;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

/**
 * @program: apiplatform
 * @description:
 * @author: JiXiYang
 * @create: 2018-05-25 16:44
 **/
@Getter
@Setter
@ToString
public class ApiPrice {
    private String price_id;
    private String api_id;
    private Integer price_type;
    private Integer content;
    private Float price;

}
