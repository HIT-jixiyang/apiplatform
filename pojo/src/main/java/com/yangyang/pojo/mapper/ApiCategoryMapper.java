package com.yangyang.pojo.mapper;

import com.yangyang.pojo.entity.ApiCategory;
import com.yangyang.pojo.provider.ApiCategoryProvider;
import org.apache.ibatis.annotations.*;

import java.util.List;

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

}
