package com.yangyang.apiplatform;

import com.yangyang.apiplatform.entity.Sp;
import com.yangyang.apiplatform.mapper.SpMapper;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest
public class ApiplatformApplicationTests {
@Autowired
	SpMapper spMapper;
	@Test
	public void contextLoads() {

Sp sp=new Sp();
sp=spMapper.getSpByEmail("1044456468@qq.com");
		System.out.println(sp.toString());
//sp.setSp_description()
/*

if(spMapper.updateSp(sp.getSp_name(),sp.getOrg_id(),sp.getDescription(),sp.getTel(),sp.getRepresentative(),sp.getEmail(),sp.getPassword(),sp.getSp_id())){
	System.out.println("修改成功");
}
*/

	}

}
