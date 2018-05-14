package com.yangyang.pojo.mapper;


import com.yangyang.pojo.entity.ApiAuthorization;
import org.apache.ibatis.annotations.*;
import org.springframework.stereotype.Component;

/**
 * @program: apiplatform
 * @description: API授权信息Mapper
 * @author: JiXiYang
 * @create: 2018-04-19 16:55
 **/
@Component
@Mapper
public interface ApiAuthorizationMapper {
    @Insert("insert into api_app(api_category_id,app_id,create_time,enabled) values(#{api_category_id},#{app_id},#{create_date})")
    public int addAuthorization(ApiAuthorization apiAuthorization);
    @Select("select * from api_app where api_category_id=#{api_id} and app_id=#{app_id}")
    public ApiAuthorization getApiAuthorizationByAppIDAndApiCategoryID(@Param(value = "api_category_id") String api_category_id, @Param(value = "app_id") String app_id);
    /*@Update("update api_app set enabled=#{enabled} where api_id=#{api_id} and app_id=#{app_id}")
    public int updateApiAuthorizationByAppIDAndApiID(@Param(value = "enabled") boolean enabled,@Param(value = "api_id") String api_id, @Param(value = "app_id")BigInteger app_id);
*/
    @Delete("delete from api_app where api_category_id=#{api_category_id} and app_id=#{app_id}")
    public int deleteApiAuthorizationByAppIDAndApiCategoryID(@Param(value = "api_category_id") String api_category_id, @Param(value = "app_id") String app_id);
    @Delete("delete from api_app where app_id=#{app_id}")
    public int deleteApiAuthorizationByAppIDAndApiID(@Param(value = "app_id") String app_id);
    @Delete("delete from api_app where api_category_id=#{api_category_id}")
    public int deleteApiAuthorizationByApiCategoryID(@Param(value = "api_category_id") String api_category_id);

}