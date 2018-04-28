package com.yangyang.apiplatform.web;

import com.yangyang.apiplatform.entity.Api;
import com.yangyang.apiplatform.entity.ApiParam;
import com.yangyang.apiplatform.entity.Consumer;
import com.yangyang.apiplatform.entity.Sp;
import com.yangyang.apiplatform.mapper.ApiMapper;
import com.yangyang.apiplatform.mapper.ConsumerMapper;
import com.yangyang.apiplatform.mapper.SpMapper;
import com.yangyang.apiplatform.service.ApiService;
import com.yangyang.apiplatform.service.LoginService;
import com.yangyang.apiplatform.utils.UUID;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpSession;
import javax.websocket.Session;
import java.lang.reflect.Method;
import java.util.List;
import java.util.Map;

import static org.bouncycastle.cms.RecipientId.password;

@RestController

public class SPController {
    @Autowired
    LoginService loginService;
    @Autowired
    ApiService apiService;
    @Autowired
    SpMapper spMapper;
    @RequestMapping(value = "/sp/sp_login", method = RequestMethod.POST)
    public ModelAndView Sp_Login(@RequestParam(value = "email") String email, @RequestParam(value = "password") String password, HttpSession session) {
        System.out.println(email);
        Sp sp = new Sp();
        sp.setSp_email(email);
        sp.setSp_password(password);
        if (loginService.existSp(sp)) {
            sp = spMapper.getSpByEmail(email);
            addSptoSession(session, sp);
            session.setMaxInactiveInterval(10 * 60);
            ModelAndView mv = new ModelAndView("/sp/sp_apimanage");
            List<Api> apilist = apiService.getApiListBySpId(loginService.findSpByEmail(email).getSp_id());
            mv.addObject("apilist", apilist);
            return mv;
        } else {
            return new ModelAndView("/sp/sp_login");
        }
    }


    @RequestMapping(value = "/sp/sp_loginpage")
    public ModelAndView enterSpLoginPage() {
        ModelAndView mv = new ModelAndView("/sp/sp_login");
        return mv;
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
    @ResponseBody
    public List<Api> getApiList(HttpSession session) {
        System.out.println(session.getAttribute("sp").toString());
        List<Api> apilist = apiService.getApiListBySpId(loginService.findSpByEmail(((Sp) session.getAttribute("sp")).getSp_email()).getSp_id());
        return apilist;
    }
    //到达添加API页面
    @RequestMapping(value = "/sp/toaddapipage")
    public ModelAndView  toaddpage(){
        ModelAndView mv=new ModelAndView("/sp/sp_addapi");
        return mv;
    }
    //添加API
    @PostMapping(value = "/sp/addapi")
    public String addApi(Map map, HttpSession session) {
        Api api= (Api) map.get("api");
        List<ApiParam> apiParamList= (List<ApiParam>) map.get("apiparamlist");
        System.out.println("前端发过来的参数" + api.toString());
        String uid = UUID.getUUID();
        api.setApi_id(uid);
        api.setSp_id(((Sp) session.getAttribute("sp")).getSp_id());
        api.setApi_enabled(1);
        api.setApi_path("/" + uid + "/**");
        System.out.println(api.toString());
        System.out.println("getSp_id:" + ((Sp) session.getAttribute("sp")).getSp_id());
        if (apiService.addApi(api,apiParamList))
            return "success";
        else
            return "fail";
    }
    //获取sp_addapi中的iframe子页面
    @RequestMapping(value = "/sp/sp_addapipagesrc")
    public ModelAndView src() {
        ModelAndView mv = new ModelAndView("/sp/addapi");
        return mv;
    }
@RequestMapping(value = "/sp/sp_apimanage")
    public ModelAndView toapimanage(){
        ModelAndView mv=new ModelAndView("/sp/sp_apimanage");
        return mv;
}
    @RequestMapping(value = "/sp/sp_appmanage")
    public ModelAndView toappmanage(){
        ModelAndView mv=new ModelAndView("/sp/sp_appmanage");
        return mv;
    }
    @RequestMapping(value = "/sp/apipeizhi")
    public ModelAndView toModifyPage(){

        ModelAndView mv=new ModelAndView("/sp/sp_apimodify");
        return mv;
    }
}
