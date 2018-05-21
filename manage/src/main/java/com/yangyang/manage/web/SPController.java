package com.yangyang.manage.web;


import com.yangyang.pojo.entity.*;
import com.yangyang.pojo.mapper.SpMapper;
import com.yangyang.pojo.service.ApiService;
import com.yangyang.pojo.service.LoginService;
import com.yangyang.utils.XmlUtil;
import com.yangyang.utils.utils.ClassUtil;
import com.yangyang.utils.utils.RandomStrUtil;
import com.yangyang.utils.utils.UUID;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpSession;
import java.util.List;
import java.util.Map;

@CrossOrigin
@RestController
public class SPController {
    @Autowired
    LoginService loginService;
    @Autowired
    ApiService apiService;
    @Autowired
    SpMapper spMapper;
    private static final org.slf4j.Logger LOGGER = LoggerFactory.getLogger(SPController.class);


    @RequestMapping(value = "/sp/sp_login", method = RequestMethod.POST)
    public Object Sp_Login(@RequestBody Map<String, String> map, HttpSession session) {
        String email = map.get("email");
        String password = map.get("password");
        LOGGER.info("someone request login" + "email:" + email + "password:" + password);
        RestResult restResult = loginService.login(email, password);
        if (restResult.getStatus() == ResultStatusCode.OK.getStatuscode()) {
            Sp sp = spMapper.getSpByEmail(email);
            addSptoSession(session, sp);
            session.setMaxInactiveInterval(10 * 60);
            return restResult;
        } else {
            return restResult;
        }
    }


    @RequestMapping(value = "/sp/sp_logout")
    public ModelAndView spLogout(HttpSession session) {
        session.removeAttribute("sp");
        ModelAndView mv = new ModelAndView("/sp/sp_login");
        return mv;
    }

    public void addSptoSession(HttpSession session, Sp sp) {
        session.setAttribute("sp", sp);
    }
    @RequestMapping(value = "/sp/getapilist", method = RequestMethod.GET)
    public Object getApiList(HttpSession session) {
        System.out.println(session.getAttribute("sp").toString());
        List<Api> apilist = apiService.getApiListBySpId(loginService.findSpByEmail(((Sp) session.getAttribute("sp")).getSp_email()).getSp_id());
        RestResult restResult = new RestResult(ResultStatusCode.OK.getStatuscode(), ResultStatusCode.OK.getMessage(), apilist);
        return restResult;
    }
    //到达添加API页面
    @RequestMapping(value = "/sp/toaddapipage")
    public ModelAndView toaddpage() {
        ModelAndView mv = new ModelAndView("/sp/sp_addapi");
        return mv;
    }

    //添加API
    @PostMapping(value = "/sp/addapi")
    public RestResult addApi(@RequestBody Map map, HttpSession session) {
        Map<String, Object> api_map = (Map<String, Object>) map.get("api");
        Api api = ClassUtil.mapToClass(api_map, Api.class);
        api.setApi_id(System.currentTimeMillis() + RandomStrUtil.getRandomString(7));
        api.setApi_enabled(1);
        api.setApi_path("");
        api.setApi_verify_state(0);
        api.setApi_adapt_state(0);
        api.setApi_time_algorithm_score((float) 0);
        api.setApi_stable_algorithm_score((float) 0);
        api.setApi_cost_algorithm_score((float) 0);
        api.setApi_success_response_ratio((float) 0);
        api.setApi_env(0);

        LOGGER.info("添加的api信息:" + api.toString());
        RestResult restResult1 = XmlUtil.getHeadersAndQuerysFromXml(api.getApi_param_xml());
        if (restResult1.getStatus() == 0) {
            return restResult1;//xml校验失败，不能添加至数据库
        }
        if (apiService.addApi(api)) {
            RestResult restResult = new RestResult(ResultStatusCode.OK.getStatuscode(), ResultStatusCode.OK.getMessage(), null);
            return restResult;
        }
        //return "success";
        else {
            RestResult restResult = new RestResult(ResultStatusCode.SYSTEM_ERROR.getStatuscode(), ResultStatusCode.SYSTEM_ERROR.getMessage(), null);
            return restResult;
        }

    }


    @PostMapping(value = "/sp/modify-api")
    public RestResult modifyApi(@RequestBody Map map) {
        String sp_id = (String) map.get("sp_id");
        Map api_map = (Map) map.get("api");
        Api api = ClassUtil.mapToClass(api_map, Api.class);
        try{
            apiService.updateApi(api);
            return new RestResult(1,"修改成功",null);
        }catch (Exception e){
            return new RestResult(0,"修改失败",e.toString());
        }
    }
}
