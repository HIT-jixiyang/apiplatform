package com.yangyang.apiselector;

import com.yangyang.apiselector.service.ComputScoreByModelService;
import com.yangyang.pojo.entity.Api;
import com.yangyang.pojo.entity.BillItem;
import com.yangyang.pojo.mapper.ApiMapper;
import com.yangyang.pojo.mapper.BillItemMapper;
import com.yangyang.pojo.service.BillItemService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest
public class ApiselectorApplicationTests {
	@Autowired
	BillItemMapper billItemMapper;
	@Autowired
	BillItemService billItemService;
	@Autowired
	ApiMapper apiMapper;
	@Autowired
	ComputScoreByModelService computScoreByModelService;
	@Test
	public void contextLoads() {
		System.out.println(billItemMapper.getAverageResponseTimeByApiID(10,"ef7deaca96d94cfeb21c1985c44525db"));
		System.out.println(billItemMapper.getResponseTimesByApiIDAndStatusCode(10,"ef7deaca96d94cfeb21c1985c44525db","200"));
	/*	Api api=new Api();
		api=apiMapper.getApiByApiID("ef7deaca96d94cfeb21c1985c44525db");
		api.setApi_average_response_time(new Float(0.44));
		apiMapper.updateApiByApiExample(api);*/
		BillItem billItem=new BillItem();
		billItem.setBill_item_id("1525861097960fkRTgtBJlz");
		billItem.setResponse_code("404");
	billItemService.updateBillItem(billItem);
	}
	@Test
	public void ComputTest(){
		computScoreByModelService.Comput();
	}

}
