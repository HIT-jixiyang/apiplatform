package com.yangyang.apiplatform.web;

import com.yangyang.apiplatform.entity.Consumer;
import com.yangyang.apiplatform.mapper.ConsumerMapper;
import com.yangyang.apiplatform.service.MyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.actuate.metrics.CounterService;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpSession;
import java.util.Map;

@RestController
public class ConsumerController {
    @Autowired
    ConsumerMapper consumerMapper;
    @Autowired
    MyService myService;

    @RequestMapping(value = "/consumer/consumer_login", method = RequestMethod.POST)
    public String Consumer_Login(@RequestBody Map map, HttpSession session) {
        //counterService.increment("consumer_log");

        String email = (String) map.get("consumer_email");
        String password = (String) map.get("consumer_password");
        System.out.println(email);
        Consumer consumer;
        consumer = consumerMapper.getConsumerByEmail(email);
        System.out.println("从数据库读出来消费者信息" + consumer.toString());
        if (consumer != null) {
            if (consumer.getConsumer_password().equals(password)) {
                System.out.println("消费者登陆成功");
                return "success";
            } else
                return "error";
        } else return "error";

    }

    @RequestMapping(value = "/consumer/consumer_loginpage")
    public ModelAndView enterConsumerLoginPage() {
        myService.exampleMethod();
        ModelAndView mv = new ModelAndView("/consumer/consumer_login");
        return mv;
    }

    @RequestMapping(value = "/consumer/consumer_logout")
    public ModelAndView consumerLogout(HttpSession session) {
        session.removeAttribute("consumer");
        ModelAndView mv = new ModelAndView("/sp/sp_login");
        return mv;
    }

    @RequestMapping(value = "/consumer/consumer_center")
    public ModelAndView enterConsumerCenter() {
        ModelAndView mv = new ModelAndView("/consumer/consumer_houtai_myapi");
        return mv;
    }

    @RequestMapping(value = "/consumer/consumer_houtai_apicenter")
    public ModelAndView enterConsumerApiCenter() {
        ModelAndView mv = new ModelAndView("/consumer/consumer_houtai_apicenter");
        return mv;
    }

    @RequestMapping(value = "/consumer/consumer_houtai_appmanage")
    public ModelAndView enterConsumerAppManage() {
        ModelAndView mv = new ModelAndView("/consumer/consumer_houtai_appmanage");
        return mv;
    }
}



