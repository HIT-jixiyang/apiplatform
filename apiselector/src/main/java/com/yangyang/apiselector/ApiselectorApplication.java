package com.yangyang.apiselector;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.scheduling.annotation.EnableScheduling;

@SpringBootApplication(scanBasePackages = {"com.yangyang"})
@EnableScheduling
@EnableAsync
@MapperScan("com.yangyang.pojo.mapper")
public class ApiselectorApplication {

	public static void main(String[] args) {
		SpringApplication.run(ApiselectorApplication.class, args);
	}
}
