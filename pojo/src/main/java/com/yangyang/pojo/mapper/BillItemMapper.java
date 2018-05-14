package com.yangyang.pojo.mapper;


import com.yangyang.pojo.entity.BillItem;
import com.yangyang.pojo.provider.BillItemProvider;
import org.apache.ibatis.annotations.*;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Map;

@Mapper
public interface BillItemMapper {
 //插入账单条目
 @Insert("insert into bill_item(bill_item_id,api_id,app_id) values(#{bill_item_id},#{api_id},#{app_id})")
public int addBillItem(BillItem bill_item);
 //根据appID与apiid查询账单列表
 @Select("select * from bill_item where app_id=#{app_id} and api_id=#{api_id}")
 public List<BillItem> getBillItemListByAppIDAndApiID(@Param(value = "app_id") String app_id, @Param(value = "api_id") String api_id);
@SelectProvider(type = BillItemProvider.class,method = "getBillItemPageListByBillItemExample")
public List<BillItem> getBillItemListByBillItemExample(Integer pageNo, Integer pageSize, BillItem billItem);
@SelectProvider(type = BillItemProvider.class,method = "getBillItemPageListByBillItemExampleAndTime")
public List<Map> getBillItemPageListByBillItemExampleAndTime(Integer pageNo, Integer pageSize, BillItem billItem,String api_name,String beginTime,String endTime);
@SelectProvider(type = BillItemProvider.class,method = "getBillItemCountByAppIDAndApiName")
public Integer getBillItemCountByAppIDAndApiName(BillItem billItem, String api_name,String beginTime, String endTime);
@UpdateProvider(type = BillItemProvider.class,method = "updateBillItemByBillExample")
public int updateBillItemByBillExample(BillItem billItem);
@SelectProvider(type = BillItemProvider.class,method ="getAverageResponseTimeByApiID" )
public Float getAverageResponseTimeByApiID(int offset, String api_id);
@SelectProvider(type = BillItemProvider.class,method = "getResponseTimesByApiIDAndStatusCode")
public int getResponseTimesByApiIDAndStatusCode(int offset, String api_id, String response_code);

}
