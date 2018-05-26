package com.yangyang.pojo.entity;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.io.Serializable;
@Setter
@Getter
@ToString
public class ApiParam implements Serializable
{
    String api_param_id;
    String api_id;
    String api_param_key;
    String api_param_value;
    Integer api_param_ismust;
    String api_param_type;
    String api_param_format;
    String api_param_desc;
    Integer api_param_position;




}
