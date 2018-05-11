package com.yangyang.apiselector.service;



import com.yangyang.pojo.entity.Api;
import com.yangyang.pojo.entity.ApiCategory;

import java.util.List;
import java.util.Map;

/**
 * @program: apiplatform
 * @description: 提供最好的api的信息给网关
 * @author: JiXiYang
 * @create: 2018-05-09 20:22
 **/
public interface BestApiService {
    //根据api类别和选择的策略返回最好的api
    public List<Api> getBestApiByApiCategoryAndStrategy(ApiCategory apiCategory, int Strategy);

}
