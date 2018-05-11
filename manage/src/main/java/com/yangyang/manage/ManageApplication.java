package com.yangyang.manage;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.transaction.annotation.EnableTransactionManagement;

@SpringBootApplication
@EnableTransactionManagement
@ComponentScan(basePackages = {"com.yangyang.*"})
@MapperScan(basePackages = {"com.yangyang.*"})
public class ManageApplication {
	public static void main(String[] args) {
		SpringApplication.run(ManageApplication.class, args);
	}
}
