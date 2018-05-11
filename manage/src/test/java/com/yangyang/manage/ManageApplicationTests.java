package com.yangyang.manage;

import com.yangyang.pojo.entity.BillItem;
import com.yangyang.pojo.mapper.BillItemMapper;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest
public class ManageApplicationTests {
@Autowired
BillItemMapper billItemMapper;
	@Test
	public void contextLoads() {
		System.out.println(billItemMapper.getAverageResponseTimeByApiID(10,"ef7deaca96d94cfeb21c1985c44525db"));
		System.out.println(billItemMapper.getResponseTimesByApiIDAndStatusCode(10,"ef7deaca96d94cfeb21c1985c44525db","200"));
	}

}
