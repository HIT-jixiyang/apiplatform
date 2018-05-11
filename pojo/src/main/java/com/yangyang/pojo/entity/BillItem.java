package com.yangyang.pojo.entity;


import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.io.Serializable;

@Getter
@Setter
@ToString
public class BillItem implements Serializable{
  private String bill_item_id;
  private java.sql.Timestamp create_time;
  private String api_id;
  private String app_id;
  private Float request_time;
  private String response_code;
}
