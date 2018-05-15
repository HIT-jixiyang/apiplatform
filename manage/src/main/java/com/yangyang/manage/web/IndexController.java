package com.yangyang.manage.web;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;
@CrossOrigin
@Controller
public class IndexController {
    @RequestMapping(value = "/index",method = RequestMethod.GET)
    public ModelAndView Index(){
        return new ModelAndView("index");
    }
}
