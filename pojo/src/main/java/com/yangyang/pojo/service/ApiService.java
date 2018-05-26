package com.yangyang.pojo.service;


import com.yangyang.pojo.entity.Api;
import com.yangyang.pojo.entity.ApiParam;
import com.yangyang.pojo.entity.ApiPrice;
import com.yangyang.pojo.entity.RestResult;
import com.yangyang.pojo.mapper.ApiMapper;
import com.yangyang.pojo.mapper.ApiParamMapper;
import com.yangyang.pojo.mapper.ApiPriceMapper;
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
    @Autowired
    LeafService leafService;
    @Autowired
    ApiPriceMapper apiPriceMapper;
    @Autowired
    ApiParamService apiParamService;

    //插入API同时插入参数列表
    @Transactional
    public boolean addApi(Api api, ApiPrice apiPrice, List<ApiParam> list) {
        if (!apiMapper.addApi(api) || !apiPriceMapper.addApiPrice(apiPrice))
            return false;
        String normal_response = api.getApi_normal_return_demo();
        try {
            leafService.addOriginLeafInfosByJsonExample(normal_response, api.getApi_id());
            apiParamService.updateParamByApiID(list, api.getApi_id());
        } catch (Exception e) {
            System.out.println(e.getMessage());
            return false;
        }
        return true;
    }

    public List<Api> getApiListBySpId(String sp_id) {
        return apiMapper.getApiListBySpId(sp_id);
    }

    public boolean updateApi(Api api) {
        return apiMapper.updateApiByApiExample(api) == 1 ? true : false;
    }

    public List<Api> getAllApi() {
        return apiMapper.getAllApiList();
    }

    public Map<String, Object> getApiPageList(Integer pageNo, Integer pageSize, Api api, String key) {
        Map<String, Object> result = new HashMap<>();
        result.put("data", apiMapper.getApiPageListByApiExample(pageNo, pageSize, api, key));
        result.put("total", apiMapper.countPageList(api, key));
        return result;
    }

    public Map<String, Object> getApiList(Integer pageNo, Integer pageSize, Api api) {
        Map<String, Object> result = new HashMap<>();
        result.put("data", apiMapper.getApiListByApiExample(pageNo, pageSize, api));
        result.put("total", apiMapper.countPageList(api, null));
        return result;
    }

    public Map getApidetailByApiID(String api_id) {
        return apiMapper.getApidetailByApiID(api_id);
    }

    @Transactional
    public void deleteApiByApiID(String api_id) {
        apiMapper.deleteApiByApiID(api_id);
        apiParamMapper.deleteApiParamByApiID(api_id);
    }

    public Api getApiByApiID(String api_id) {
        return apiMapper.getApiByApiID(api_id);
    }

    public float getApiAverageResponseTime() {

        return (float) 1.0;
    }

    public List<Map> getApiListByCategoryID(String categoryID) {
        Api api = new Api();
        api.setApi_category_id(categoryID);
        return apiMapper.getApiAndPriceListByApiExample(api);
    }

    public List<Map> getAllApiAndPriceList() {
        return apiMapper.getAllApiAndApiPriceList();
    }

    public Float getAverageCostByCategoryID(String api_category_id) {
        return apiMapper.getAverageCostByCategoryID(api_category_id);
    }

    public Float getAverageReponseTimeByCategoryID(String api_category_id) {
        return apiMapper.getAverageResponseTimeByCategoryID(api_category_id);
    }

    public Float getAverageSuccessRatioByCategoryID(String api_category_id) {
        return apiMapper.getAverageSuccessRatioByCategoryID(api_category_id);
    }

    public Api getApiByCostAlgorithmAndApiCategoryID(String api_category_id) {
        List<Api> apiList = apiMapper.getLowestCostApiList(api_category_id, 1);
        return apiList.get(0);
    }

    public Api getApiByTimeAlgorithmAndApiCategoryID(String api_category_id) {
        List<Api> apiList = apiMapper.getMostFastApiList(api_category_id, 1);
        return apiList.get(0);
    }

    public Api getApiByStableAlgorithmAndApiCategoryID(String api_category_id) {
        List<Api> apiList = apiMapper.getMostStableApiList(api_category_id, 1);
        return apiList.get(0);
    }
}
