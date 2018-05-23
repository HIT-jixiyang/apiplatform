package com.yangyang.pojo.entity;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.sql.Timestamp;

@ToString
@Setter
@Getter
public class ApiCategory {
  private String api_category_id;
  private String api_category_name;
  private String api_category_desc;
  private String api_category_path;
  private Float api_category_avg_response_time;
  private Integer api_category_comment;
  private String api_category_total_times;
  private Float api_category_price;
  private Integer api_category_bill_type;
  private Integer api_category_request_type;
  private Integer api_category_enabled;
  private String api_category_normal_response;
  private String api_category_error_response;
  private String api_category_param_xml;
  private Timestamp api_category_create_time;
}
