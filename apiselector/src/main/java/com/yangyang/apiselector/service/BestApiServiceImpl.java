package com.yangyang.apiselector.service;

import com.yangyang.pojo.entity.Api;
import com.yangyang.pojo.entity.ApiCategory;
import com.yangyang.pojo.mapper.ApiMapper;
import com.yangyang.pojo.service.ApiService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @program: apiplatform
 * @description:
 * @author: JiXiYang
 * @create: 2018-05-09 20:32
 **/
@Service
public class BestApiServiceImpl implements BestApiService {
    @Autowired
    ApiMapper apiMapper;
    @Autowired
    ApiService apiService;
    @Override
    public List<Api> getBestApiByApiCategoryAndStrategy(ApiCategory apiCategory, int strategy) {
        switch (strategy){
        case 0:return apiMapper.getLowestCostApiList(apiCategory.getApi_category_id(),10);
        case 1:return apiMapper.getMostFastApiList(apiCategory.getApi_category_id(),10);
        case 2:return apiMapper.getMostStableApiList(apiCategory.getApi_category_id(),10);
        default:return null;
}
    }


}
