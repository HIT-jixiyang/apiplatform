package com.yangyang.manage;

import com.yangyang.pojo.entity.App;
import com.yangyang.pojo.entity.BillItem;
import com.yangyang.pojo.entity.RestResult;
import com.yangyang.pojo.mapper.BillItemMapper;
import com.yangyang.pojo.service.AppService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.web.client.RestTemplate;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RunWith(SpringRunner.class)
@SpringBootTest
public class ManageApplicationTests {
@Autowired
BillItemMapper billItemMapper;
AppService appService;
RestTemplate restTemplate=new RestTemplate();
	@Test
	public void contextLoads() {
		System.out.println(billItemMapper.getAverageResponseTimeByApiID(10,"ef7deaca96d94cfeb21c1985c44525db"));
		System.out.println(billItemMapper.getResponseTimesByApiIDAndStatusCode(10,"ef7deaca96d94cfeb21c1985c44525db","200"));
	}
	@Test
	public  void testGetAppList(){
		App app=new App();
		app.setConsumer_id("87de254182574856af64d323869b686d");
		Map<String, Object> appList=appService.getAppPageList(1,10,app,null);
	}
	@Test
	public void TestXml(){
		Map<String,Object> map=new HashMap<>();
		map.put("param_xml","<standardparam>\n" +
				"    <headers>\n" +
				"        <param key='app_id' type='String' ismust='true' desc=' '>19329829</param>\n" +
				"        <param key='app_secret' type='String' ismust='true' desc=' '>ankalala</param>\n" +
				"        <param key='time_stamp' type='Long' ismust= 'true' desc=' '>132973982</param>\n" +
				"    </headers>\n" +
				"    <querys>\n" +
				"    </querys>\n" +
				"    <body>\n" +
				"        <param key='city' type='String' format=''  ismust='true'>北京</param>\n" +
				"        <param key='date' type='String' format='YYMMDDHH'  ismust='false'>2018-05-21 </param>\n" +
				"        <param key='list' type='List'>\n" +
				"            <param key='listitem1' type='String'>listitem1 </param>\n" +
				"            <param key='listitem2' type='String'> listitem2</param>\n" +
				"            <param key='listitem3' type='String'>listitem3 </param>\n" +
				"        </param>\n" +
				"        <param key='list' type='Object'>\n" +
				"            <param key='listitem1' type='String'>listitem </param>\n" +
				"            <param key='listitem2' type='String'> listitem2</param>\n" +
				"            <param key='listitem3' type='String'>listitem3 </param>\n" +
				"        </param>\n" +
				"    </body>\n" +
				"\n" +
				"</standardparam>");
	String restResult=  restTemplate.postForObject("http://127.0.0.1:10002/admin/test-param-xml",map,String.class);

	}

}
