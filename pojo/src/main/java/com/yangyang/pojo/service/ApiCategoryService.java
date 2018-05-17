package com.yangyang.pojo.service;

import com.yangyang.pojo.entity.ApiCategory;
import com.yangyang.pojo.entity.RestResult;
import com.yangyang.pojo.entity.ResultStatusCode;
import com.yangyang.pojo.entity.StandardInboundParam;
import com.yangyang.pojo.mapper.ApiCategoryMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

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
    @Autowired
    StandardInboundParamService standardInboundParamService;
    public List<String> getAllCategoryID(){
        return  apiCategoryMapper.getAllCategoryID();
    }
@Transactional
    public RestResult addApiCategory(ApiCategory apiCategory, List<StandardInboundParam> paramList){
        if (getApiCategoryByPath(apiCategory.getApi_category_path())!=null){
            return new RestResult(0,"路径重复，添加失败",null);
        }else {
            if(apiCategoryMapper.addApiCategory(apiCategory)!=1||!standardInboundParamService.ModifyStandardParamList(apiCategory.getApi_category_id(),paramList)){
                return new RestResult(0,"内部错误，添加失败",null);
            }
        }
        return new RestResult(1,"OK",apiCategory.getApi_category_id());
    }
    public List<ApiCategory> getApiCategoryListByApiCategoryExample(Integer pageNum, Integer pageSize, ApiCategory apiCategory){
        return apiCategoryMapper.getApiCategoryListByApiCategoryExample(pageNum,pageSize,apiCategory);
    }
    public int updateApiCategoryByApiExample(ApiCategory apiCategory){
        return apiCategoryMapper.updateApiCategoryByApiExample(apiCategory);
    }
    public Map<String,Object> getApiCategoryPageListByApiCategoryExample(Integer pageNo, Integer pageSize, ApiCategory apiCategory,String name){
        Map<String,Object> map=new HashMap<>();
        map.put("data",apiCategoryMapper.getApiCategoryPageListByApiCategoryExample(pageNo,pageSize,apiCategory,name));
        map.put("total",apiCategoryMapper.countPageList(apiCategory,name));
        return map;
    }
public List<ApiCategory> getAllApiCategory(){
        return apiCategoryMapper.getAllApiCategory();
}
public ApiCategory getApiCategoryByPath(String path){
    return apiCategoryMapper.getApiCategoryByPath(path);
}
}
