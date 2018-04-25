package com.yangyang.apiplatform.mapper;

import com.yangyang.apiplatform.entity.ApiRequestParam;
import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
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
    @Select("select api_param,api_param_demo,api_param_position,api_param_ismust from api_request_param where api_id = #{apiId} and api_param_isconstant<>1")
    List<ApiRequestParam> getApiParamListById(String apiId);
    //插入一条参数
    @Insert("insert into api_request_param values(#{api_id},#{api_param},#{api_param_demo},#{api_param_position},#{api_param_ismust},#{api_param_isconstant})")
    public boolean addApiParam(ApiRequestParam apiRequestParam);
    @Delete("delete from api_request_param where api_id=#{api_id}")
    public int deleteApiParamByApiID(String api_id);
    @Select("select api_param,api_param_demo,api_param_position,api_param_ismust from api_request_param where api_id = #{apiId} and api_param_isconstant=1")
    public List<ApiRequestParam> getConstantParam();
}
