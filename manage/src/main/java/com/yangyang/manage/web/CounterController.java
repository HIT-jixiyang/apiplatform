package com.yangyang.manage.web;


import com.yangyang.pojo.service.MyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
@CrossOrigin
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
