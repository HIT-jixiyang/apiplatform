package com.yangyang.manage.web;

import com.yangyang.pojo.service.ApiService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;
@CrossOrigin
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
