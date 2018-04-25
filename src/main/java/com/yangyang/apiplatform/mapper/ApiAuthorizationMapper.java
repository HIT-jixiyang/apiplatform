package com.yangyang.apiplatform.mapper;

import com.yangyang.apiplatform.entity.Api;
import com.yangyang.apiplatform.entity.ApiAuthorization;
import org.apache.ibatis.annotations.*;

import java.math.BigInteger;

/**
 * @program: apiplatform
 * @description: API授权信息Mapper
 * @author: JiXiYang
 * @create: 2018-04-19 16:55
 **/
@Mapper
public interface ApiAuthorizationMapper {


    @Insert("insert into api_app(api_id,app_id,create_time,enabled) values(#{api_id},#{app_id},#{create_date})")
    public int addAuthorization(ApiAuthorization apiAuthorization);
    @Select("select * from api_app where api_id=#{api_id} and app_id=#{app_id}")
    public ApiAuthorization getApiAuthorizationByAppIDAndApiID(@Param(value = "api_id") String api_id, @Param(value = "app_id")String app_id);
    /*@Update("update api_app set enabled=#{enabled} where api_id=#{api_id} and app_id=#{app_id}")
    public int updateApiAuthorizationByAppIDAndApiID(@Param(value = "enabled") boolean enabled,@Param(value = "api_id") String api_id, @Param(value = "app_id")BigInteger app_id);
*/
    @Delete("delete from api_app where api_id=#{api_id} and app_id=#{app_id}")
    public int deleteApiAuthorizationByAppIDAndApiID(@Param(value = "api_id") String api_id, @Param(value = "app_id")String app_id);
}
