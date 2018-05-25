package com.yangyang.pojo.entity;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class Leaf {
  private Integer leaf_id;
  private String api_id;
  private String api_category_id;
  private String parent_type;
  private String leaf_key;
  private String leaf_format;
  private Object leaf_value;
  private String leaf_type;
  private String leaf_path;
}
