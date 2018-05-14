package com.yangyang.pojo.service;


import com.yangyang.pojo.entity.BillItem;
import com.yangyang.pojo.mapper.BillItemMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @program: apiplatform
 * @description: 账单条目service
 * @author: JiXiYang
 * @create: 2018-05-01 17:22
 **/
@Service
public class BillItemService {
    @Autowired
    BillItemMapper billItemMapper;
    public boolean addBillItem(BillItem billItem){
        return billItemMapper.addBillItem(billItem)==1?true:false;
    }
    public boolean updateBillItem(BillItem billItem){
        return billItemMapper.updateBillItemByBillExample(billItem)==1?true:false;
    }
    public Map<String,Object> getBillItemByAppIDAndApiIDAndTime(Integer pageNo,Integer pageSize,BillItem billItem, String api_name,String beginTime, String endTime)
    {
        Map<String,Object> map=new HashMap<>();
        map.put("total",billItemMapper.getBillItemCountByAppIDAndApiName(billItem,api_name,beginTime,endTime));
        map.put("data",billItemMapper.getBillItemPageListByBillItemExampleAndTime(pageNo,pageSize, billItem,api_name,beginTime,endTime));
    return map;
    }
}
