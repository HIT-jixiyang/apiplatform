package com.yangyang.pojo.mapper;


import com.yangyang.pojo.entity.ApiAuthorization;
import com.yangyang.pojo.entity.ApiCategory;
import com.yangyang.pojo.provider.ApiAuthorizationProvider;
import org.apache.ibatis.annotations.*;
import org.springframework.stereotype.Component;

import java.util.List;

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
    @Select("select * from api_app where api_category_id=#{api_category_id} and app_id=#{app_id}")
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
   //传入页码，页面大小，app_id获得一页信息
    @SelectProvider(type = ApiAuthorizationProvider.class,method ="getApiCategoryListPageByAppID" )
    public List<ApiCategory> getApiCategoryListByAppID(Integer pageNo, Integer pageSize, String app_id);
    @SelectProvider(type = ApiAuthorizationProvider.class,method = "getApiCategoryCountByAppID")
    public int getApiCategoryCount(String app_id);

}
