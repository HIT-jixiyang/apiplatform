package com.yangyang.apiplatform.web;

import com.yangyang.apiplatform.entity.Api;
import com.yangyang.apiplatform.entity.ApiRequestParam;
import com.yangyang.apiplatform.entity.Sp;
import com.yangyang.apiplatform.service.ApiService;
import com.yangyang.apiplatform.utils.ClassUtil;
import com.yangyang.apiplatform.utils.UUID;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpSession;
import java.util.List;
import java.util.Map;

@RestController
public class ApiController {
    @Autowired
    ApiService apiService;

    @PostMapping(value = "/sp/addapi")
    public String addApi(Api api, HttpSession session) {
        System.out.println("前端发过来的参数" + api.toString());
        String uid = UUID.getUUID();
        api.setApi_id(uid);
        api.setSp_id(((Sp) session.getAttribute("sp")).getSp_id());
        api.setApi_strip_prefix(1);
        api.setApi_enabled(1);
        api.setApi_path("/" + uid + "/**");
        api.setApi_retryable(1);
        System.out.println(api.toString());
        System.out.println("getSp_id:" + ((Sp) session.getAttribute("sp")).getSp_id());
        if (apiService.addApi(api))
            return "success";
        else
            return "fail";
    }

    @RequestMapping(value = "/sp/addapipage")
    public ModelAndView toaddapi() {
        ModelAndView mv = new ModelAndView("/sp/addapi");
        return mv;
    }

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
    @RequestMapping(value = "/getData/search", method = RequestMethod.POST)
    @ResponseBody
    public Object getApiPageList(@RequestBody Map map) {
        return apiService.getApiPageList((Integer) map.get("pageNo"), (Integer) map.get("pageSize"), ClassUtil.mapToClass((Map) map.get("api"), Api.class));
    }
}
