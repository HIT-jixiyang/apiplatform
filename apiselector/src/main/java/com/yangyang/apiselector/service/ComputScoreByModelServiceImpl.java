package com.yangyang.apiselector.service;

import com.yangyang.apiselector.ApiselectorApplication;
import com.yangyang.pojo.entity.Api;
import com.yangyang.pojo.entity.ApiCategory;
import com.yangyang.pojo.mapper.ApiCategoryMapper;
import com.yangyang.pojo.service.ApiService;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

/**
 * @program: apiplatform
 * @description:
 * @author: JiXiYang
 * @create: 2018-05-11 09:39
 **/

@Service
public class ComputScoreByModelServiceImpl implements ComputScoreByModelService {
    private static final org.slf4j.Logger LOGGER = LoggerFactory.getLogger(ComputScoreByModelService.class);
    @Autowired
    ApiService apiService;
    @Autowired
    ApiCategoryMapper apiCategoryMapper;

    @Override
    @Scheduled(fixedDelay = 60000)
    public void Comput() {
        LOGGER.info("---------------------开始重新计算api分数---------------------------");
        List<String> apiCategoryIDs = apiCategoryMapper.getAllCategoryID();
        Float apiCategoryAverageCost;
        Float apiCategoryAverageResponseTime;
        Float apiCategoryAverageSuccessRatio;
        Float average_response_time;
        Float api_price;
        Float api_success_response_ratio;
        Integer content;
        String api_id;
        for (String api_category_id : apiCategoryIDs) {
            apiCategoryAverageCost = apiService.getAverageCostByCategoryID(api_category_id);
            apiCategoryAverageResponseTime = apiService.getAverageReponseTimeByCategoryID(api_category_id);
            apiCategoryAverageSuccessRatio = apiService.getAverageSuccessRatioByCategoryID(api_category_id);
            List<Map> mapList = apiService.getApiListByCategoryID(api_category_id);
            if (mapList.size() != 0) {
                for (Map map : mapList) {
                    api_id = (String) map.get("api_id");
                    content = (Integer) map.get("content");//api单价对应的配额
                    api_price = (Float) map.get("price");//api单价
                    average_response_time = (Float) map.get("api_average_response_time");//api平均响应时间
                    api_success_response_ratio = (Float) map.get("api_success_response_ratio");//api响应成功率
                    Float x = (api_price / content) / apiCategoryAverageCost;
                    Float y = average_response_time / apiCategoryAverageResponseTime;
                    Float z = api_success_response_ratio / apiCategoryAverageSuccessRatio;
                    Float api_cost_algorithm_score = new Float((1 / x) * 0.7 + (1 / y) * 0.15 + z * 0.15);
                    Float api_time_algorithm_score = new Float((1 / x) * 0.15 + (1 / y) * 0.7 + z * 0.15);
                    Float api_stable_algorithm_score = new Float((1 / x) * 0.15 + (1 / y) * 0.15 + z * 0.7);
                    Api api = new Api();
                    api.setApi_id(api_id);
                    api.setApi_cost_algorithm_score(api_cost_algorithm_score);
                    api.setApi_stable_algorithm_score(api_stable_algorithm_score);
                    api.setApi_time_algorithm_score(api_time_algorithm_score);
                    apiService.updateApi(api);
                }
            }
        }
    }
}
