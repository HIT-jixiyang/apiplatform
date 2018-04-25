package com.yangyang.apiplatform;

import com.codahale.metrics.MetricRegistry;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.actuate.metrics.dropwizard.DropwizardMetricServices;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.zuul.EnableZuulProxy;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Primary;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.transaction.annotation.EnableTransactionManagement;

@EnableZuulProxy
@SpringBootApplication
@EnableScheduling
@EnableTransactionManagement
public class ApiplatformApplication {

	public static void main(String[] args) {
		SpringApplication.run(ApiplatformApplication.class, args);

	}
//	@Bean(name = "dropwizardMetricServices")
//	public DropwizardMetricServices dropwizardMetricServices(MetricRegistry metricRegistry){
//		return new DropwizardMetricServices(metricRegistry);
//	}
}
