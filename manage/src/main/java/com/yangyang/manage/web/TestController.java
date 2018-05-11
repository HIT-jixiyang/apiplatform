package com.yangyang.manage.web;


import com.yangyang.utils.utils.HTTPUtils;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
public class TestController {
    @RequestMapping(value = "/api/request",method = RequestMethod.POST)
    @ResponseBody
    public Map<String,Object> TestApi(@RequestBody Map map){
        System.out.println("执行到test");
        String url=(String) map.get("url");
        System.out.println(url);
        if(!url.startsWith("http")){
            url="http://"+url;
        }
        String method= (String) map.get("method");
        List<Map> headerParam= (List<Map>) map.get("hp");
        List<Map> bodyParam= (List<Map>) map.get("bp");
        Map<String,Object> resultmap=new HashMap<String, Object>();
switch (method){
    case "get":
  resultmap= HTTPUtils.doget(url,headerParam);
       return resultmap;
    case "post":
        resultmap=HTTPUtils.dopost(url,headerParam,bodyParam);
        return resultmap;
}
        return null;
    }
    @RequestMapping(value = "/api/test")

    public ModelAndView Test(){
        ModelAndView mv=new ModelAndView("apitest");
        return mv;
    }

}
