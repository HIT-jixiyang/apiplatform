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
    List<Map> getApiPageListByApiExample(Integer pageNo, Integer pageSize, ApiCategory apiCategory);

    // 获取api数
    @SelectProvider(type = ApiCategoryProvider.class, method = "countPageList")
    Integer countPageList(Api api);

}
