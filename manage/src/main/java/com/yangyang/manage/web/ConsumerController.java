package com.yangyang.manage.web;


import com.yangyang.pojo.entity.*;
import com.yangyang.pojo.mapper.ConsumerMapper;
import com.yangyang.pojo.service.*;
import com.yangyang.utils.utils.ClassUtil;
import com.yangyang.utils.utils.OrderID;
import com.yangyang.utils.utils.RandomStrUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpSession;
import java.util.HashMap;
import java.util.Map;

@RestController
public class ConsumerController {
    @Autowired
    ConsumerMapper consumerMapper;
    @Autowired
    MyService myService;
    @Autowired
    AppService appService;
    @Autowired
    ApiService apiService;
    @Autowired
    AuthorizationService authorizationService;
    @Autowired
    OrderService orderService;

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
                session.setAttribute("consumer", consumer);
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
    public Map<String, Object> addApp(@RequestBody App app, HttpSession session) {
        Map<String, Object> map = new HashMap<>();
        Consumer sessionconsumer = (Consumer) session.getAttribute("consumer");
        app.setConsumer_id(sessionconsumer.getConsumer_id());
        app.setApp_secret(RandomStrUtil.getRandomString(16));
        if (appService.addApp(app)) {
            map.put("status", "success");
        } else {
            map.put("status", "error");
        }
        return map;
    }

    //根据前台传过来的参数找App列表，参数应该包括Consumer_id,第几页，每页多少
    @PostMapping(value = "/consumer/get_applist_by_consumer_id")
    @ResponseBody
    public Map<String, Object> getAppPageListByConsumerId(@RequestBody Map map, HttpSession session) {
        Consumer consumer = (Consumer) session.getAttribute("consumer");
        App app = new App();
        app.setConsumer_id(consumer.getConsumer_id());
        Map<String, Object> appMap = appService.getAppPageList((Integer) map.get("pageNo"), (Integer) map.get("pageSize"), app);
        return appMap;
    }

    @PostMapping(value = "/consumer/delete_app_by_app_id")
    @ResponseBody
    public RestResult deleteAppByAppID(@RequestBody Map<String, String> map) {
        String app_id = map.get("app_id");
        if (appService.deleteByApp_id(app_id)) {
            return new RestResult(ResultStatusCode.OK.getStatuscode(), ResultStatusCode.OK.getMessage(), null);
        }
        return new RestResult(ResultStatusCode.SYSTEM_ERROR.getStatuscode(), ResultStatusCode.SYSTEM_ERROR.getMessage(), null);
    }

    @PostMapping(value = "/consumer/update_app_by_app_id")
    @ResponseBody
    public RestResult updateAppByAppID(@RequestBody Map<String, String> map) {
        String app_id = map.get("app_id");
        String app_name = map.get("app_name");
        String app_description = map.get("app_description");
        App app = new App();
        app.setApp_id(app_id);
        app.setApp_name(app_name);
        app.setApp_description(app_description);
        if (appService.updateAppByAppID(app)) {
            return new RestResult(ResultStatusCode.OK.getStatuscode(), ResultStatusCode.OK.getMessage(), null);
        }
        return new RestResult(ResultStatusCode.SYSTEM_ERROR.getStatuscode(), ResultStatusCode.SYSTEM_ERROR.getMessage(), null);
    }

    @RequestMapping(value = "/consumer/apilist")
    public ModelAndView getConsumerApplist() {
        ModelAndView mv = new ModelAndView("consumer_apilist");
        return mv;
    }

    @RequestMapping(value = "/consumer/buyapi")
    public String buyApi(@RequestBody Map map) {
        String consumer_id = (String) map.get("consumer_id");
        String api_category_id = (String) map.get("api_category_id");
        Long total = (Long) map.get("total");
        Order order = new Order();
        order.setOrder_id(OrderID.getOrderID());
        order.setConsumer_id(consumer_id);
        order.setApi_category_id(api_category_id);
        order.setOrder_remain(total);
        order.setOrder_total(total);
        if (orderService.addOrder(order)) {
            return "success";
        } else return "error";
    }

    @RequestMapping(value = "/consumer/getapilist", method = RequestMethod.POST)
    @ResponseBody
    public Object getApiPageList(@RequestBody Map map) {
        return apiService.getApiPageList((Integer) map.get("pageNo"), (Integer) map.get("pageSize"), ClassUtil.mapToClass((Map) map.get("api"), Api.class));
    }

    @RequestMapping(value = "/consumer/addApiAuthorization")
    public String addApiAuthorization(@RequestBody Map map) {
        String ApiCategory_id = (String) map.get("api_category_id");
        String App_id = (String) map.get("app_id");
        ApiAuthorization apiAuthorization = new ApiAuthorization();
        apiAuthorization.setApi_category_id(ApiCategory_id);
        apiAuthorization.setApp_id(App_id);
        apiAuthorization.setEnabled(true);
        if (authorizationService.addApiAuthorization(apiAuthorization)) {
            return "success";
        } else return "error";
    }
}



