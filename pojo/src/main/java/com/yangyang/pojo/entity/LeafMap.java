package com.yangyang.pojo.entity;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class LeafMap {
  private Integer origin_leaf_id;
  private Integer standard_leaf_id;
  private String api_id;
  private String origin_leaf_format;
  private String standard_leaf_format;
  private String origin_leaf_type;
}