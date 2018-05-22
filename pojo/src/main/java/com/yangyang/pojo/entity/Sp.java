package com.yangyang.pojo.entity;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@ToString
@Getter
@Setter
//serviceprovider实体类
public class Sp {
    private String sp_id;
    private String sp_org_id;
    private String sp_description;
    private String sp_tel;
    private String sp_representative;
    private String sp_email;
    private String sp_name;
    private String sp_password;
    private String sp_representative_id;
    private java.sql.Timestamp sp_create_time;
    private Integer sp_state;

}

