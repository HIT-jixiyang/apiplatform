package com.yangyang.apigateway;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.zuul.EnableZuulProxy;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.core.task.AsyncTaskExecutor;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;
import org.springframework.stereotype.Component;

import java.util.concurrent.RejectedExecutionHandler;
import java.util.concurrent.ThreadPoolExecutor;

@SpringBootApplication
@EnableScheduling
@EnableZuulProxy
@EnableAsync
@ComponentScan(basePackages = {"com.yangyang.*"})
@MapperScan(basePackages = {"com.yangyang.*"})
public class ApigatewayApplication {
	@Bean
	public AsyncTaskExecutor taskExecutor() {
		ThreadPoolTaskExecutor executor = new ThreadPoolTaskExecutor();
		executor.setThreadNamePrefix("Anno-Executor");
		executor.setMaxPoolSize(20);

		// 设置拒绝策略
		executor.setRejectedExecutionHandler(new RejectedExecutionHandler() {
			@Override
			public void rejectedExecution(Runnable r, ThreadPoolExecutor executor) {
				// .....
			}
		});
		// 使用预定义的异常处理类
		// executor.setRejectedExecutionHandler(new ThreadPoolExecutor.CallerRunsPolicy());

		return executor;
	}
	public static void main(String[] args) {
		SpringApplication.run(ApigatewayApplication.class, args);
	}

}
