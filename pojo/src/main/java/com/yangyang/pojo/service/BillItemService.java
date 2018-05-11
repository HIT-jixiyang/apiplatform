package com.yangyang.pojo.service;


import com.yangyang.pojo.entity.BillItem;
import com.yangyang.pojo.mapper.BillItemMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

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
}
