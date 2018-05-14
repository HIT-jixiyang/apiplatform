package com.yangyang.manage;

import com.yangyang.pojo.entity.App;
import com.yangyang.pojo.entity.BillItem;
import com.yangyang.pojo.mapper.BillItemMapper;
import com.yangyang.pojo.service.AppService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;
import java.util.Map;

@RunWith(SpringRunner.class)
@SpringBootTest
public class ManageApplicationTests {
@Autowired
BillItemMapper billItemMapper;
AppService appService;
	@Test
	public void contextLoads() {
		System.out.println(billItemMapper.getAverageResponseTimeByApiID(10,"ef7deaca96d94cfeb21c1985c44525db"));
		System.out.println(billItemMapper.getResponseTimesByApiIDAndStatusCode(10,"ef7deaca96d94cfeb21c1985c44525db","200"));
	}
	@Test
	public  void testGetAppList(){
		App app=new App();
		app.setConsumer_id("87de254182574856af64d323869b686d");
		Map<String, Object> appList=appService.getAppPageList(1,10,app);
	}

}
