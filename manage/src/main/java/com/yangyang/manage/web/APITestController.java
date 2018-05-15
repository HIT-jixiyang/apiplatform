package com.yangyang.manage.web;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.Map;

/**
 * @program: apiplatform
 * @description:
 * @author: JiXiYang
 * @create: 2018-05-06 15:57
 **/
@CrossOrigin
@Controller
public class APITestController {
    @RequestMapping(value = "/add")
    public String testdata(@RequestBody Map map){

        return "";
    }
}
