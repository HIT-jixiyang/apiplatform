package com.yangyang.manage.web;

import com.yangyang.pojo.entity.RestResult;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.Map;

/**
 * @program: apiplatform
 * @description:
 * @author: JiXiYang
 * @create: 2018-05-15 09:19
 **/
@CrossOrigin
@Controller
public class AdminController {
    @PostMapping(value = "/admin/add-apicategory")
    public RestResult addApiCategory(@RequestBody Map<String,Object> map){

        return  null;
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
