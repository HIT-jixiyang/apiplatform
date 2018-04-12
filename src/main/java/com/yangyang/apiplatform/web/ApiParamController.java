package com.yangyang.apiplatform.web;

import com.yangyang.apiplatform.service.ApiParamService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

@RestController
public class ApiParamController {
    @Autowired
    ApiParamService apiParamService;

    @RequestMapping(value = "/getData/api/param", method = RequestMethod.POST)
    public Object getApiParamById(@RequestBody Map map){
        return apiParamService.getApiParamById((String)map.get("apiId"));
    }
}