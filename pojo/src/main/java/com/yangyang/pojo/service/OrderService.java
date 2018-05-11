package com.yangyang.pojo.service;


import com.yangyang.pojo.entity.Order;
import com.yangyang.pojo.mapper.OrderMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class OrderService {
    @Autowired
    OrderMapper orderMapper;
 @Transactional
public boolean addOrder(Order order){
Order order1=orderMapper.getOrderByConsumerIDAndApiID(order);
     if (order1!=null){
         System.out.println("已经购买，准备续约"+order1.toString());
        if (orderMapper.addTotalByOrder(order)==1){
            return true;
        }else {
            return false;
        }
     }
     else {
         if(orderMapper.addOrder(order)==1){
             return true;
         }
         else {
             return false;
         }
     }

}
public List<Order> getOrderListByConsumerID(String consumer_id){
    return  orderMapper.getOrderListByConsumerID(consumer_id);
}
public  List<Order> getOrderListByApiID(String api_id){
    return orderMapper.getOrderListByApiID(api_id);
}

}
