package com.yangyang.manage.web;

import com.yangyang.pojo.service.ApiParamService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Map;
@CrossOrigin
@RestController
public class ApiParamController {
    @Autowired
    ApiParamService apiParamService;

    @RequestMapping(value = "/getData/api/param", method = RequestMethod.POST)
    public Object getApiParamById(@RequestBody Map map){
        return apiParamService.getApiParamListById((String)map.get("apiId"));
    }
}