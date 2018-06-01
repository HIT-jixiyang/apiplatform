package com.yangyang.apigateway;

import com.yangyang.apigateway.service.PerSecondCountService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.web.client.RestTemplate;

@RunWith(SpringRunner.class)
public class ApigatewayApplicationTests {
/*@Autowired
	PerSecondCountService perSecondCountService;*/
	@Test
	public void contextLoads() {
		while (true){
			try {
				Thread.sleep(1000);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
			RestTemplate restTemplate=new RestTemplate();
			String count=restTemplate.getForObject("http://localhost:11001/monitor/getPerSecondRequestTimes?api_id=1527598249582WZVf1dq",String.class);
			System.out.println(count);
			//System.out.println(perSecondCountService.getRequestSpeedByCounterName("speed.second.ef7deaca96d94cfeb21c1985c44525db"));
		}
	}
}
