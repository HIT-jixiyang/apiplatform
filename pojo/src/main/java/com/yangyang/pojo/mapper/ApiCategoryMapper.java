package com.yangyang.pojo.mapper;

import com.yangyang.pojo.entity.Api;
import com.yangyang.pojo.entity.ApiCategory;
import com.yangyang.pojo.provider.ApiCategoryProvider;
import com.yangyang.pojo.provider.ApiProvider;
import org.apache.ibatis.annotations.*;

import java.util.List;
import java.util.Map;

/**
 * @program: apiplatform
 * @description:
 * @author: JiXiYang
 * @create: 2018-05-11 15:37
 **/
@Mapper
public interface ApiCategoryMapper {
    @Select("select  api_category_id from api_category")
    public List<String> getAllCategoryID();

    @InsertProvider(type = ApiCategoryProvider.class, method = "insertApiCategory")
    public int addApiCategory(ApiCategory apiCategory);

    @SelectProvider(type = ApiCategoryProvider.class, method = "getApiCategoryListByApiCategoryExample")
    public List<ApiCategory> getApiCategoryListByApiCategoryExample(Integer pageNum, Integer pageSize, ApiCategory apiCategory);

    @UpdateProvider(type = ApiCategoryProvider.class, method = "updateApiCategoryByApiExample")
    public int updateApiCategoryByApiExample(ApiCategory apiCategory);

    // 获取分页列表
    @SelectProvider(type = ApiCategoryProvider.class, method = "getApiCategoryPageListByApiCategoryExample")
    List<ApiCategory> getApiCategoryPageListByApiCategoryExample(Integer pageNo, Integer pageSize, ApiCategory apiCategory, String name);

    // 获取apicategoey数
    @SelectProvider(type = ApiCategoryProvider.class, method = "countPageList")
    Integer countPageList(ApiCategory apiCategory, String name);

    @Delete("delete from api_category where api_category_id=#{api_category_id}")
    Integer deleteApiCategory(@Param(value = "api_category_id") String api_category_id);

    @Select("select * from api_category")
    public List<ApiCategory> getAllApiCategory();

    @Select("SELECT * FROM api_category WHERE api_category_path=#{api_category_path}")
    public ApiCategory getApiCategoryByPath(@Param("api_category_path") String api_category_path);
}
