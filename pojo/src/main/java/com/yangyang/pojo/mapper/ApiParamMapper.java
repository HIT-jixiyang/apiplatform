package com.yangyang.pojo.mapper;

import com.yangyang.pojo.entity.ApiParam;
import com.yangyang.pojo.provider.ApiParamProvider;
import org.apache.ibatis.annotations.*;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * @Author: ny
 * @Date: Created in 19:24 2018/4/7 0007
 */
@Component
@Mapper
public interface ApiParamMapper {
    // 根据Id获取api参数列表
    @Select("select * from h2_api_param where api_id = #{apiId}")
    List<ApiParam> getApiParamListById(String apiId);
    //插入一条参数
   /* @Insert("insert into api_param values(#{api_param_id},#{api_id}," +
            "#{api_pre_param_key}"+
            "#{api_after_param_key},#{api_param_value}," +
            "#{api_pre_param_position},#{api_after_param_position}" +
            ",#{api_param_ismust}" +",#{api_param_isconstant}"+
            ")")*/
   @InsertProvider(type = ApiParamProvider.class,method = "insertApiParam")
    public boolean addApiParam(ApiParam apiRequestParam);
    @Delete("delete from h2_api_param where api_id=#{api_id}")
    public int deleteApiParamByApiID(String api_id);
}
