package com.yangyang.pojo.entity;


import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Setter
@Getter
@ToString
public class StandardInboundParam {
  private String standard_inbound_param_id;
  private String standard_inbound_param_key;
  private String standard_inbound_param_type;
  private String api_category_id;
  private String standard_inbound_param_desc;
  private Integer standard_inbound_param_position;
  private String standard_inbound_param_value_demo;
  private Integer standard_inbound_param_ismust;
}
