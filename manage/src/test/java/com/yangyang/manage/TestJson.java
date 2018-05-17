package com.yangyang.manage;

import com.alibaba.fastjson.JSONObject;
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
    public void test(){
        ApiCategory apiCategory=new ApiCategory();
        apiCategory.setApi_category_bill_type(1);
        apiCategory.setApi_category_desc("获得图片中的物体信息");
        apiCategory.setApi_category_path("/tuxiangshibie");
        apiCategory.setApi_category_price((float) 0.01);
        apiCategory.setApi_category_name("图像识别");
        apiCategory.setApi_category_normal_response("{}");
        apiCategory.setApi_category_error_response("{}");
        apiCategory.setApi_category_request_type(1);
        apiCategory.setApi_category_bill_type(0);
        String json= JSONObject.toJSONString(apiCategory);
        System.out.println(json);
        StandardInboundParam standardInboundParam=new StandardInboundParam();
        standardInboundParam.setStandard_inbound_param_desc("desc1");
        standardInboundParam.setStandard_inbound_param_key("key1");
        standardInboundParam.setStandard_inbound_param_position(0);
        standardInboundParam.setStandard_inbound_param_value_demo("value1");
        standardInboundParam.setStandard_inbound_param_type("String");
        System.out.println(JSONObject.toJSONString(standardInboundParam));
    }
}
