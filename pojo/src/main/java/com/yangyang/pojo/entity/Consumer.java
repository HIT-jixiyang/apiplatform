package com.yangyang.pojo.entity;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class Consumer {
    private String consumer_id;
    private String consumer_name;
    private String consumer_password;
    private String consumer_email;
    private String consumer_tel;
    private Integer consumer_type;
    private Integer consumer_state;
    private String consumer_intro;
    private String consumer_card_id;
    private java.sql.Timestamp consumer_create_time;

}
