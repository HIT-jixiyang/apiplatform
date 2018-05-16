package com.yangyang.pojo.service;


import com.yangyang.pojo.entity.Api;
import com.yangyang.pojo.entity.ApiParam;
import com.yangyang.pojo.mapper.ApiMapper;
import com.yangyang.pojo.mapper.ApiParamMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class ApiService {
    @Autowired
    ApiMapper apiMapper;
    @Autowired
    ApiParamMapper apiParamMapper;

    //插入API同时插入参数列表
    @Transactional
    public boolean addApi(Api api, List<ApiParam> apiParamList) {
        boolean flag = false;
        if (!apiMapper.addApi(api)) return false;

        for (ApiParam p : apiParamList) {
            p.setApi_id(api.getApi_id());
            //由于常量参数不变，所以只有后端参数，不对用户可见
            if(p.getApi_param_isconstant()){
                p.setApi_pre_param_key("");
                p.setApi_pre_param_position(0);

            }
            if (!apiParamMapper.addApiParam(p)) return false;
        }
        return true;

    }

    public List<Api> getApiListBySpId(String sp_id) {
        return apiMapper.getApiListBySpId(sp_id);
    }

    public boolean updateApi(Api api) {
        return apiMapper.updateApiByApiExample(api)==1?true:false;
    }

    public List<Api> getAllApi() {
        return apiMapper.getAllApiList();
    }

    public Object getApiPageList(Integer pageNo, Integer pageSize, Api api) {
        Map<String, Object> result = new HashMap<>();
        result.put("data", apiMapper.getApiPageListByApiExample(pageNo, pageSize, api));
        result.put("total", apiMapper.countPageList(api));
        return result;
    }
    @Transactional
    public void deleteApiByApiID(String api_id) {
        apiMapper.deleteApiByApiID(api_id);
        apiParamMapper.deleteApiParamByApiID(api_id);
    }
    public Api getApiByApiID(String api_id){
         return  apiMapper.getApiByApiID(api_id);
    }
    public float getApiAverageResponseTime(){

        return (float) 1.0;
    }
    public List<Map> getApiListByCategoryID(String categoryID){
        Api api=new Api();
        api.setApi_category_id(categoryID);
        return apiMapper.getApiAndPriceListByApiExample(api);
    }
    public List<Map> getAllApiAndPriceList(){
        return apiMapper.getAllApiAndApiPriceList();
    }
    public Float getAverageCostByCategoryID(String api_category_id){
        return apiMapper.getAverageCostByCategoryID(api_category_id);
    }
    public Float getAverageReponseTimeByCategoryID(String api_category_id){
        return apiMapper.getAverageResponseTimeByCategoryID(api_category_id);
    }
    public Float getAverageSuccessRatioByCategoryID(String api_category_id){
        return apiMapper.getAverageSuccessRatioByCategoryID(api_category_id);
    }
    public Api getApiByCostAlgorithmAndApiCategoryID(String api_category_id){
        List<Api> apiList=  apiMapper.getLowestCostApiList(api_category_id,1);
    return apiList.get(0);
    }
    public Api getApiByTimeAlgorithmAndApiCategoryID(String api_category_id){
        List<Api> apiList=  apiMapper.getMostFastApiList(api_category_id,1);
        return apiList.get(0);
    }
    public Api getApiByStableAlgorithmAndApiCategoryID(String api_category_id){
        List<Api> apiList=  apiMapper.getMostStableApiList(api_category_id,1);
        return apiList.get(0);
    }
}
