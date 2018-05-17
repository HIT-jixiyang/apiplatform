package com.yangyang.manage.web;

import com.yangyang.pojo.entity.ApiCategory;
import com.yangyang.pojo.entity.RestResult;
import com.yangyang.pojo.entity.StandardInboundParam;
import com.yangyang.pojo.service.ApiCategoryService;
import com.yangyang.pojo.service.ApiService;
import com.yangyang.pojo.service.StandardInboundParamService;
import com.yangyang.utils.utils.ApiCategoryID;
import com.yangyang.utils.utils.ClassUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * @program: apiplatform
 * @description:
 * @author: JiXiYang
 * @create: 2018-05-15 09:19
 **/
@CrossOrigin
@RestController
public class AdminController {
    @Autowired
    StandardInboundParamService standardInboundParamService;
    @Autowired
    ApiCategoryService apiCategoryService;
    @PostMapping(value = "/admin/add-apicategory")
    public RestResult addApiCategory(@RequestBody Map<String,Object> map){

            Map<String,Object> api_category= (Map<String, Object>) map.get("api_category");
            ApiCategory apiCategory= ClassUtil.mapToClass(api_category,ApiCategory.class);
            apiCategory.setApi_category_id(ApiCategoryID.getID());
            System.out.println(apiCategory.toString());
            List<Map> paramList= (List<Map>) map.get("paramlist");
            List<StandardInboundParam> standardInboundParamList=new ArrayList<>();
            StandardInboundParam standardInboundParam=null;
            if (paramList.size()>0){
                for (Map map1 :paramList){
                    standardInboundParam=ClassUtil.mapToClass(map1,StandardInboundParam.class);
                    standardInboundParamList.add(standardInboundParam);
                }
                System.out.println(standardInboundParamList.toString());
                return  apiCategoryService.addApiCategory(apiCategory,standardInboundParamList);
            }

          return new RestResult(0,"error",null);
    }
    @PostMapping(value = "/admin/modify-apicategory")
    public RestResult modifyApiCategory(@RequestBody Map<String,Object> map){

        return  null;
    }
    //获取一页api类
    @PostMapping(value = "/admin/get-apicategorylist")
    public RestResult getApicategoryList(@RequestBody Map<String,Object> map){

        return  null;
    }
}
