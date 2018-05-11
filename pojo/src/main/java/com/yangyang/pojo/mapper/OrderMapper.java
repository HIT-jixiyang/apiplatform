package com.yangyang.pojo.mapper;



import com.yangyang.pojo.entity.Order;
import com.yangyang.pojo.provider.OrderProvider;
import org.apache.ibatis.annotations.*;
import org.springframework.stereotype.Component;

import java.util.List;
@Component
@Mapper
public interface OrderMapper {

    @Insert("insert into `order`(order_id,consumer_id,api_id,order_remain,order_total) values(#{order_id},#{consumer_id}," +
            "#{api_id},#{order_remain},#{order_total})")
    public int addOrder(Order order);
@Select("select * from `order` where consumer_id=#{consumer_id} and api_id=#{api_id}")
    public Order getOrderByConsumerIDAndApiID(Order order);
    @Select("select * from `order` where consumer_id=#{consumer_id}")
    public List<Order> getOrderListByConsumerID(String consumer_id);

    @Select("select * from `order` where api_id=#{api_id}")
    public List<Order> getOrderListByApiID(String api_id);

    @Select("delete from  `order` where consumer_id=#{consumer_id} and api_id=#{api_id}")
    public int removeOrderByConsumerIDAndApiID(@Param(value = "consumer_id") String consumer_id, @Param(value = "api_id") String api_id);

    @SelectProvider(type = OrderProvider.class, method = "getOrderPageListByOrderExample")
    public List<Order> getOrderListByOrderExample(Integer pageNo, Integer pageSize, Order order);

    @SelectProvider(type = OrderProvider.class, method = "countPageList")
    Integer countPageList(Order order);
    //续约
    @Update("update `order` set order_remain=order_remain+#{order_total},order_total=order_total+#{order_total} where order_id=#{order_id}")
    public int addTotalByOrder(Order order);
}
