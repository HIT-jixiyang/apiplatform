package com.yangyang.pojo.entity;


import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class Order {
    private String order_id;
    private String consumer_id;
    private String api_category_id;
    private java.sql.Timestamp order_create_time;
    private Long order_remain;
    private Long order_total;
    private Integer strategy;
}
