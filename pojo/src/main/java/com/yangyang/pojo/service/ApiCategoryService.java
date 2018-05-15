package com.yangyang.pojo.service;

import com.yangyang.pojo.entity.ApiCategory;
import com.yangyang.pojo.mapper.ApiCategoryMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @program: apiplatform
 * @description:
 * @author: JiXiYang
 * @create: 2018-05-14 21:25
 **/
@Service
public class ApiCategoryService {
    @Autowired
    ApiCategoryMapper apiCategoryMapper;
    public List<String> getAllCategoryID(){
        return  apiCategoryMapper.getAllCategoryID();
    }
    public int addApiCategory(ApiCategory apiCategory){
        return apiCategoryMapper.addApiCategory(apiCategory);
    }
    public List<ApiCategory> getApiCategoryListByApiCategoryExample(Integer pageNum, Integer pageSize, ApiCategory apiCategory){
        return apiCategoryMapper.getApiCategoryListByApiCategoryExample(pageNum,pageSize,apiCategory);
    }
    public int updateApiCategoryByApiExample(ApiCategory apiCategory){
        return apiCategoryMapper.updateApiCategoryByApiExample(apiCategory);
    }
    public Map<String,Object> getApiCategoryPageListByApiCategoryExample(Integer pageNo, Integer pageSize, ApiCategory apiCategory,String name){
        Map<String,Object> map=new HashMap<>();
        map.put("total",apiCategoryMapper.getApiCategoryPageListByApiCategoryExample(pageNo,pageSize,apiCategory,name));
        map.put("data",apiCategoryMapper.countPageList(apiCategory,name));
        return map;
    }

}
