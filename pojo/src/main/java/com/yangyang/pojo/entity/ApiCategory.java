package com.yangyang.pojo.entity;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@ToString
@Setter
@Getter
public class ApiCategory {
  private String api_category_id;
  private String api_category_name;
  private String api_category_desc;
  private String api_category_path;
  private Float api_category_avg_response_time;
  private Long api_category_comment;
  private String api_category_total_times;
  private Float api_category_price;
  private Long api_category_bill_type;
  private Integer api_category_request_type;
  private Integer api_category_enabled;
}
