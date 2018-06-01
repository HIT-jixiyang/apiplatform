package com.yangyang.apiselector.service;

import com.yangyang.pojo.entity.Api;
import com.yangyang.pojo.entity.ApiCategory;
import com.yangyang.pojo.entity.BillItem;
import com.yangyang.pojo.mapper.ApiMapper;
import com.yangyang.pojo.mapper.BillItemMapper;
import com.yangyang.pojo.service.ApiCategoryService;
import com.yangyang.pojo.service.ApiService;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @program: apiplatform
 * @description: 定时计算api权重更新数据库
 * @author: JiXiYang
 * @create: 2018-05-10 08:22
 **/
@Service
public class ComputWeightService {
    private static final org.slf4j.Logger LOGGER = LoggerFactory.getLogger(ComputWeightService.class);
    @Autowired
    BillItemMapper billItemMapper;
    @Autowired
    ApiMapper apiMapper;
    @Autowired
    ApiService apiService;
@Autowired
    ApiCategoryService apiCategoryService;
    @Scheduled(fixedRate = 10000)
    public void computApiAverageResponseTimeInLast300() {
        LOGGER.info("-----------开始计算近300次请求的平均请求时间---------------------");
        List<Api> apiList = apiService.getAllApi();
        for (Api api : apiList) {
            Float time = billItemMapper.getAverageResponseTimeByApiID(300, api.getApi_id());
          //  Float time1 = billItemMapper.getAverageResponseTimeByApiCategory(1000, api.getApi_category_id());

           if(time!=null){
             Api api1=new Api();
             api1.setApi_id(api.getApi_id());
             api1.setApi_average_response_time(time);
               apiMapper.updateApiByApiExample(api1);
           }
        }
    }

    @Scheduled(fixedRate = 10000)
    public void computApiCategoryAverageResponseTimeInLast1000()
    {
        LOGGER.info("----------------开始计算api类的平均响应时间-------------");
    List<String> apicategoryidlist=apiCategoryService.getAllCategoryID();
        ApiCategory apiCategory=new ApiCategory();
    for (String api_category_id:apicategoryidlist){
        Float time=billItemMapper.getAverageResponseTimeByApiCategory(1000,api_category_id);
        if(time!=null){
           apiCategory.setApi_category_id(api_category_id);
           apiCategory.setApi_category_avg_response_time(time);
            apiCategoryService.updateApiCategoryByApiExample(apiCategory);
        }
    }
    }
    public void computOkResponseTimesRateInLast1000() {
        LOGGER.info("-----------开始计算近1000次请求的成功次数---------------------");
        List<Api> apiList = apiService.getAllApi();
        if(apiList!=null){
            for (Api api : apiList) {
                BillItem billItem = new BillItem();
                billItem.setApi_id(api.getApi_id());
                List<BillItem> billItemList = billItemMapper.getBillItemListByBillItemExample(new Integer(1), new Integer(1000), billItem);
             //存在使用记录才能操作
               if (billItemList.size()!=0){
                   int times = billItemMapper.getResponseTimesByApiIDAndStatusCode(1000, api.getApi_id(), "200");
                   Float success_radio;
                   //使用记录没有1000条，就按照已经有的次数计算
                   if (billItemList.size() < 1000) {
                       success_radio = times / (float) billItemList.size();
                       api.setApi_success_response_ratio(success_radio);
                       apiService.updateApi(api);
                   }else {
                       //使用记录超过1000条，按照1000来算
                       success_radio= (float) (times/1000.0);
                       api.setApi_success_response_ratio(success_radio);
                       apiService.updateApi(api);
                   }
               }
        }

        }
    }
}
