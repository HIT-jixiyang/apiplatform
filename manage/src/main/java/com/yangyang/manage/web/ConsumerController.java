package com.yangyang.manage.web;


import com.yangyang.pojo.entity.*;
import com.yangyang.pojo.mapper.ConsumerMapper;
import com.yangyang.pojo.service.*;
import com.yangyang.utils.utils.*;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpSession;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@CrossOrigin
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
    ApiAuthorizationService apiAuthorizationService;
    @Autowired
    OrderService orderService;
    @Autowired
    ApiCategoryService apiCategoryService;
    @Autowired
    StandardInboundParamService standardInboundParamService;
    private static final org.slf4j.Logger LOGGER = LoggerFactory.getLogger(Consumer.class);
//===============================消费者注册与登录======================================================
    //消费者登录，若成功将会返回消费者完整信息
    @RequestMapping(value = "/consumer/consumer_login", method = RequestMethod.POST)
    public RestResult Consumer_Login(@RequestBody Map map, HttpSession session) {
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
                return new RestResult(ResultStatusCode.OK.getStatuscode(), ResultStatusCode.OK.getMessage(), consumer);
            } else
                return new RestResult(ResultStatusCode.LOGIN_ERROR.getStatuscode(), ResultStatusCode.LOGIN_ERROR.getMessage(), null);
        } else
            return new RestResult(ResultStatusCode.LOGIN_ERROR.getStatuscode(), ResultStatusCode.LOGIN_ERROR.getMessage(), null);

    }

//消费者登出
    @RequestMapping(value = "/consumer/consumer_logout")
    public RestResult consumerLogout(HttpSession session) {
        session.removeAttribute("consumer");
        return new RestResult(1,"退出成功",null);
    }
//消费者注册
    @RequestMapping(value = "/consumer/consumer_register", method = RequestMethod.POST)
    public RestResult ConsumerRegister(@RequestBody Consumer consumer) {
        System.out.println(consumer.toString());
        consumer.setConsumer_id(UUID.getUUID());
        try{
            if (consumerMapper.addConsumer(consumer) == 1) {
                return new RestResult(1,"注册成功",consumer);
            } else return new RestResult(0,"注册失败",null);
        }catch (Exception e){
            return new RestResult(0,"注册失败",e.toString());
        }
    }
    //==================================消费者功能菜单==============================================
  /*
消费者的主菜单应该包括
服务中心：用户查看网关提供的api服务（这里指apicategory），并在服务详情页选择自己的app进行授权操作
应用管理：在这里创建app，删除app，编辑app信息，查看app已经获得的api授权情况，查看api详情
监控中心：用户看到自己已经在使用的api的健康状态，过去一段时间内的api服务使用量
账单中心：这里可以看到自己的历史访问记录
个人信息管理：编辑自己的可编辑信息
 */
    //添加app
    @PostMapping(value = "/consumer/addapp")
    @ResponseBody
    public RestResult addApp(@RequestBody Map map, HttpSession session) {
        String app_name = (String) map.get("app_name");
        String app_desc = (String) map.get("app_description");
        String consumer_id = (String) map.get("consumer_id");
        App app = new App();
        app.setApp_id(AppID.getAppID());
        app.setApp_name(app_name);
        app.setApp_description(app_desc);
        app.setConsumer_id(consumer_id);
        app.setApp_secret(RandomStrUtil.getRandomString(16));
        if (appService.addApp(app)) {
            return new RestResult(ResultStatusCode.OK.getStatuscode(), ResultStatusCode.OK.getMessage(), null);
        } else {
            return new RestResult(ResultStatusCode.SYSTEM_ERROR.getStatuscode(), ResultStatusCode.SYSTEM_ERROR.getMessage(), null);
        }
    }

    //根据前台传过来的参数找App列表，参数应该包括Consumer_id,第几页，每页多少，还有一个关键字模糊查询
    @PostMapping(value = "/consumer/get_applist_by_consumer_id")
    @ResponseBody
    public Map<String, Object> getAppPageListByConsumerId(@RequestBody Map map, HttpSession session) {
        String consumer_id = (String) map.get("consumer_id");
        App app = new App();
        String name= (String) map.get("name");
        app.setConsumer_id(consumer_id);
        Map<String, Object> appMap = appService.getAppPageList((Integer) map.get("pageNo"), (Integer) map.get("pageSize"), app,name);
        return appMap;
    }

    //根据前台传过来的app_id参数找api类列表，参数应该包括app_id,第几页，每页多少
    @PostMapping(value = "/consumer/get_api_categorylist_by_app_id")
    @ResponseBody
    public Map<String, Object> getApiCategoryPageListByAppId(@RequestBody Map map, HttpSession session) {
        Map<String, Object> appMap = apiAuthorizationService.getApiCategoryPageListByApp_id((Integer) map.get("pageNo"), (Integer) map.get("pageSize"), (String) map.get("app_id"));
        return appMap;
    }

//根据app_id删除app，注意删除app的同时就会删除授权表中的app的相关授权信息。
    @PostMapping(value = "/consumer/delete_app_by_app_id")
    @ResponseBody
    public RestResult deleteAppByAppID(@RequestBody Map<String, String> map) {
        String app_id = map.get("app_id");
        if (appService.deleteByApp_id(app_id)) {
            return new RestResult(ResultStatusCode.OK.getStatuscode(), ResultStatusCode.OK.getMessage(), null);
        }
        return new RestResult(ResultStatusCode.SYSTEM_ERROR.getStatuscode(), ResultStatusCode.SYSTEM_ERROR.getMessage(), null);
    }
//更新app信息
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


  /*  @RequestMapping(value = "/consumer/buyapi")
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
*/
/*
    @RequestMapping(value = "/consumer/getapilist", method = RequestMethod.POST)
    @ResponseBody
    public Object getApiPageList(@RequestBody Map map) {
        return apiService.getApiPageList((Integer) map.get("pageNo"), (Integer) map.get("pageSize"), ClassUtil.mapToClass((Map) map.get("api"), Api.class));
    }
*/
//传入app_id与api_category_id添加一条api服务对于app的授权信息
    @RequestMapping(value = "/consumer/addApiAuthorization")
    public RestResult addApiAuthorization(@RequestBody Map map) {
        String ApiCategory_id = (String) map.get("api_category_id");
        String App_id = (String) map.get("app_id");
        ApiAuthorization apiAuthorization = new ApiAuthorization();
        apiAuthorization.setApi_category_id(ApiCategory_id);
        apiAuthorization.setApp_id(App_id);
        apiAuthorization.setEnabled(true);
        if (apiAuthorizationService.addApiAuthorization(apiAuthorization)) {
            return new RestResult(1,"授权成功",null);
        } else return new RestResult(0,"授权失败",null);
    }
//根据api服务（api_category）的名字和页码以及每页的显示数获得api_category的列表
    @RequestMapping(value = "/consumer/get-apicategory-list", method = RequestMethod.POST)
    public RestResult getApiCategoryPageList(@RequestBody Map<String, Object> map) {
        Integer pageNo = (Integer) map.get("pageNo");
        Integer pageSize = (Integer) map.get("pageSize");
        String name = (String) map.get("api_category_name");
        Map<String, Object> map1 = null;
        try {
            map1 = apiCategoryService.getApiCategoryPageListByApiCategoryExample(pageNo, pageSize, new ApiCategory(), name);
        } catch (Exception e) {
            RestResult restResult = new RestResult(ResultStatusCode.SYSTEM_ERROR.getStatuscode(), ResultStatusCode.SYSTEM_ERROR.getMessage(), e);
        }

        RestResult restResult = new RestResult(ResultStatusCode.OK.getStatuscode(), ResultStatusCode.OK.getMessage(), map1);
        return restResult;
    }
//
    @PostMapping(value = "/consumer/get-stand-params")
    public RestResult getStandardParamListByApiCategoryID(@RequestBody Map map) {
        String api_category_id = (String) map.get("api_category_id");
        try {
            List<StandardInboundParam> paramList = standardInboundParamService.getStandardParamListByApiCategoryID(api_category_id);
            List<StandardInboundParam> headerparams = new ArrayList<>();
            List<StandardInboundParam> pathparams = new ArrayList<>();
            StandardInboundParam bodyparam = new StandardInboundParam();
            for (StandardInboundParam param : paramList) {
                switch (param.getStandard_inbound_param_position()) {
                    case 0:
                        headerparams.add(param);
                        break;
                    case 1:
                        pathparams.add(param);
                        break;
                    case 2:
                        bodyparam = param;
                }
            }
            Map<String, Object> map1 = new HashMap<>();
            map1.put("header", headerparams);
            map1.put("path", pathparams);
            map1.put("body", bodyparam);
            RestResult restResult = new RestResult(ResultStatusCode.OK.getStatuscode(), ResultStatusCode.OK.getMessage(), map1);
            return restResult;
        } catch (Exception e) {
            RestResult restResult = new RestResult(ResultStatusCode.SYSTEM_ERROR.getStatuscode(), ResultStatusCode.SYSTEM_ERROR.getMessage(), e);
            return restResult;
        }


        //return null;
    }

    @PostMapping(value = "/consumer/get-apicategory-detail")
    public RestResult getApiCategoryDetailByApiCategoryID(@RequestBody Map map) {
        String api_category_id = (String) map.get("api_category_id");
        try {
            List<StandardInboundParam> paramList = standardInboundParamService.getStandardParamListByApiCategoryID(api_category_id);
            List<StandardInboundParam> headerparams = new ArrayList<>();
            List<StandardInboundParam> queryparams = new ArrayList<>();
            StandardInboundParam bodyparam = new StandardInboundParam();
            for (StandardInboundParam param : paramList) {
                switch (param.getStandard_inbound_param_position()) {
                    case 0:
                        headerparams.add(param);
                        break;
                    case 1:
                        queryparams.add(param);
                        break;
                    case 2:
                        bodyparam = param;
                }
            }
            Map<String, Object> map1 = new HashMap<>();
            if (headerparams != null) {
                map1.put("header", headerparams);
            } else {
                map1.put("header", "[]");
            }
            if (queryparams != null) {
                map1.put("query", queryparams);
            } else {
                map1.put("query", "[]");
            }
            if (bodyparam.getStandard_inbound_param_value_demo() != null) {
                map1.put("body", bodyparam.getStandard_inbound_param_value_demo());
            } else map1.put("body", "{}");

            Map<String, Object> detail_map = new HashMap<>();
            detail_map.put("params", map1);
            Map<String, Object> responseMap = new HashMap<>();
            responseMap.put("normal_response", "{'info':'这是正常的返回报文样例，此处省略一千字'}");
            responseMap.put("error_response", "{'info':'这是异常的返回报文样例，此处省略一千字'}");
            detail_map.put("response", responseMap);
            RestResult restResult = new RestResult(ResultStatusCode.OK.getStatuscode(), ResultStatusCode.OK.getMessage(), detail_map);
            return restResult;
        } catch (Exception e) {
            RestResult restResult = new RestResult(ResultStatusCode.SYSTEM_ERROR.getStatuscode(), ResultStatusCode.SYSTEM_ERROR.getMessage(), e);
            return restResult;
        }


        //return null;
    }
}



