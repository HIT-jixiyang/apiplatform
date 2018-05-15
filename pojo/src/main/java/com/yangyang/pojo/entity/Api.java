package com.yangyang.pojo.entity;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.awt.geom.FlatteningPathIterator;

@Getter
@Setter
@ToString
public class Api {
    private String api_id;
    private String sp_id;
    private String api_token;
    private Integer api_max_in;
    private Integer api_enabled;
    private String api_description;
    private String api_path;
    private String api_name;
    private Integer api_bill_type;
    private Integer api_method;
    private String api_url;
    private String api_return_pattern;
    private String api_normal_return_demo;
    private String api_error_return_demo;
    private Integer api_timeout;
    private String api_category_id;
    private Float api_average_response_time;
    private Integer api_ok_response_times;
    private Float api_success_response_ratio;
    private Float api_time_algorithm_score;
    private Float api_stable_algorithm_score;
    private Float api_cost_algorithm_score;
    private String api_jar_path;
}
