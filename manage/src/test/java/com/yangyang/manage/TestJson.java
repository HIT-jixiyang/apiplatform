package com.yangyang.manage;

import com.alibaba.fastjson.JSONObject;
import com.yangyang.pojo.entity.Api;
import com.yangyang.pojo.entity.ApiCategory;
import com.yangyang.pojo.entity.StandardInboundParam;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.junit4.SpringRunner;

/**
 * @program: apiplatform
 * @description:
 * @author: JiXiYang
 * @create: 2018-05-17 21:04
 **/
@RunWith(SpringRunner.class)
public class TestJson {
    @Test
    public void test() {
        ApiCategory apiCategory = new ApiCategory();
        apiCategory.setApi_category_bill_type(1);
        apiCategory.setApi_category_desc("获得图片中的物体信息");
        apiCategory.setApi_category_path("/tuxiangshibie");
        apiCategory.setApi_category_price((float) 0.01);
        apiCategory.setApi_category_name("图像识别");
        apiCategory.setApi_category_normal_response("{}");
        apiCategory.setApi_category_error_response("{}");
        apiCategory.setApi_category_request_type(1);
        apiCategory.setApi_category_bill_type(0);
        String json = JSONObject.toJSONString(apiCategory);
        System.out.println(json);
        StandardInboundParam standardInboundParam = new StandardInboundParam();
        standardInboundParam.setStandard_inbound_param_desc("desc1");
        standardInboundParam.setStandard_inbound_param_key("key1");
        standardInboundParam.setStandard_inbound_param_position(0);
        standardInboundParam.setStandard_inbound_param_value_demo("value1");
        standardInboundParam.setStandard_inbound_param_type("String");
        System.out.println(JSONObject.toJSONString(standardInboundParam));
        Api api = new Api();
        api.setApi_name("test");
        api.setApi_description("test");
        api.setApi_url("http://test.com");
        api.setApi_normal_return_demo("{}");
        api.setApi_error_return_demo("{}");
        api.setApi_max_in(100);
        api.setApi_method(0);
        api.setApi_bill_type(0);
        api.setApi_param_xml("<?xml version='1.0' encoding='UTF-8' ?> <standardparam> <headers><header key='app_id' type='String' ismust='true' desc=' '>19329829</header><header key='app_secret' type='String' ismust='true' desc=' '>ankalala</header><header key='time_stamp' type='Long' ismust= 'true' desc=' '>132973982</header> </headers> </standardparam>");
        api.setApi_return_pattern("application/json");
        api.setSp_id("c430c9776a934ff1a856360185920c5d");
        System.out.println(JSONObject.toJSONString(api));
    }
}
