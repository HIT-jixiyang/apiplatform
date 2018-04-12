package com.yangyang.apiplatform.web;

import com.yangyang.apiplatform.service.MyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class CounterController {
    @Autowired
    MyService myService;

    @RequestMapping(value = "/count")
    public String Count() {
        System.out.println("被访问");
        myService.exampleMethod();
        return "success";
    }

}
