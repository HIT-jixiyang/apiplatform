package com.yangyang.apiplatform.mapper;

import com.yangyang.apiplatform.entity.ApiRequestParam;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import java.util.List;

/**
 * @Author: ny
 * @Date: Created in 19:24 2018/4/7 0007
 */
@Mapper
public interface ApiParamMapper {
    // 根据Id获取api参数列表
    @Select("select api_param,api_param_demo from api_request_param where api_id = #{apiId}")
    List<ApiRequestParam> getApiParamById(String apiId);
}
