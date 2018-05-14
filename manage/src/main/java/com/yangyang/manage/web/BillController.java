package com.yangyang.manage.web;

import com.yangyang.pojo.entity.BillItem;
import com.yangyang.pojo.service.BillItemService;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;

/**
 * @program: orderandbill
 * @description: 记账
 * @author: JiXiYang
 * @create: 2018-05-02 10:14
 **/
@RestController
public class BillController {
    private static final org.slf4j.Logger LOGGER = LoggerFactory.getLogger(BillController.class);
    @Autowired
    BillItemService billItemService;
   //记账请求
    @PostMapping(value = "/addbill")
    public Map<String,Object> addBillItemByConsumerIDAndApiID(@RequestBody Map map){
    String bill_item_id= (String) map.get("bill_item_id");
    String api_id= (String) map.get("api_id");
    String app_id= (String) map.get("app_id");
    BillItem billItem=new BillItem();
    billItem.setBill_item_id(bill_item_id);
    billItem.setApi_id(api_id);
    billItem.setApp_id(app_id);
        System.out.println(billItem.toString());
       Map<String,Object> map1=new HashMap<>();
        if( billItemService.addBillItem(billItem)){
           map1.put("status","ok");
       }
       else {
           map1.put("status","error");
       }
       return map1;
    }
    @PostMapping(value = "/updatebill")
    public Map<String,Object> updateBillByBill(@RequestBody Map map){
        String bill_item_id= (String) map.get("bill_item_id");
        Float request_time= new Float((Double) map.get("request_time"));
        String response_code= (String) map.get("response_code");
        BillItem billItem=new BillItem();
        billItem.setBill_item_id(bill_item_id);
        billItem.setResponse_code(response_code);
        billItem.setRequest_time(request_time);
        LOGGER.info(billItem.toString());
        Map<String,Object> map1=new HashMap<>();
        if( billItemService.updateBillItem(billItem)){
            map1.put("status","ok");
        }
        else {
            map1.put("status","error");
        }
        return map1;
    }
}