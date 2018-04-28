package com.yangyang.apiplatform.web;

import com.yangyang.apiplatform.service.ApiService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

@RestController
public class ApiController {
    @Autowired
    ApiService apiService;


    // api搜索
    @RequestMapping(value = "/api/search")
    public ModelAndView toapisearch() {
        ModelAndView mv = new ModelAndView("apisearch");
        return mv;
    }

    // api详情
    @RequestMapping(value = "/api/detail")
    public ModelAndView toapidetail() {
        ModelAndView mv = new ModelAndView("apidetail");
        return mv;
    }

    // 获取Api列表

}
