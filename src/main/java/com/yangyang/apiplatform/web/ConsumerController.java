package com.yangyang.apiplatform.web;

import com.yangyang.apiplatform.entity.App;
import com.yangyang.apiplatform.entity.Consumer;
import com.yangyang.apiplatform.mapper.ConsumerMapper;
import com.yangyang.apiplatform.service.AppService;
import com.yangyang.apiplatform.service.MyService;
import com.yangyang.apiplatform.utils.ClassUtil;
import com.yangyang.apiplatform.utils.RandomStrUtil;
import org.apache.catalina.servlet4preview.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.actuate.metrics.CounterService;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpSession;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
public class ConsumerController {
    @Autowired
    ConsumerMapper consumerMapper;
    @Autowired
    MyService myService;
    @Autowired
    AppService appService;
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
                session.setAttribute("consumer",consumer);
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
@PostMapping(value = "/consumer/addapp")
@ResponseBody
public Map<String,Object> addApp(@RequestBody App app, HttpSession session){
    Map<String,Object> map=new HashMap<>();
Consumer sessionconsumer= (Consumer) session.getAttribute("consumer");
    app.setConsumer_id(sessionconsumer.getConsumer_id());
    app.setApp_secret(RandomStrUtil.getRandomString(16));
    if(appService.addApp(app)){
            map.put("status","success");
        }else {
        map.put("status","error");
        }
        return map;
}
//根据前台传过来的参数找App列表，参数应该包括Consumer_id,第几页，每页多少
@PostMapping(value = "/consumer/get_applist_by_consumer_id")
@ResponseBody
public Map<String,Object> getAppPageListByConsumerId(@RequestBody Map map , HttpSession session){
    Consumer consumer= (Consumer) session.getAttribute("consumer");
    App app=new App();
    app.setConsumer_id(consumer.getConsumer_id());
    Map<String,Object> appMap=appService.getAppPageList((Integer) map.get("pageNo"),(Integer) map.get("pageSize"), app);
    return appMap;
}
@RequestMapping(value = "/consumer/apilist")
    public ModelAndView getConsumerApplist(){
    ModelAndView mv=new ModelAndView("consumer_apilist");
    return mv;
}
}



