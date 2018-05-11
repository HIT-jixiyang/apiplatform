package com.yangyang.pojo.mapper;

import com.yangyang.pojo.entity.ApiParam;
import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
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
    @Select("select * where api_id = #{apiId} and api_param_isconstant<>1")
    List<ApiParam> getApiParamListById(String apiId);
    //插入一条参数
    @Insert("insert into api_param values(#{api_param_id},#{api_id}," +
            "#{api_pre_param_key}"+
            "#{api_after_param_key},#{api_param_value}," +
            "#{api_pre_param_position},#{api_after_param_position}" +
            ",#{api_param_ismust}" +",#{api_param_isconstant}"+
            ")")
    public boolean addApiParam(ApiParam apiRequestParam);
    @Delete("delete from api_param where api_id=#{api_id}")
    public int deleteApiParamByApiID(String api_id);
    @Select("select * from api_param where api_id = #{apiId} and api_param_isconstant=1")
    public List<ApiParam> getConstantParam(String api_id);
}
