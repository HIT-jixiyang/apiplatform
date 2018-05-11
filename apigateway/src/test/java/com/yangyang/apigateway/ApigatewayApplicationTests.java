package com.yangyang.apigateway;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.web.client.RestTemplate;

@RunWith(SpringRunner.class)
public class ApigatewayApplicationTests {

	@Test
	public void contextLoads() {
		while (true){
			try {
				Thread.sleep(1000);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
			RestTemplate restTemplate=new RestTemplate();
			String count=restTemplate.getForObject("http://localhost:10000/monitor/getPerSecondRequestTimes?counter_name=counter.speed.second.ef7deaca96d94cfeb21c1985c44525db",String.class);
			System.out.println(count);
		}
	}
}
