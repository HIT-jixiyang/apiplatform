package com.yangyang.pojo.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.actuate.metrics.CounterService;
import org.springframework.stereotype.Service;

@Service
public class MyService {
    @Autowired() @Qualifier("dropwizardMetricServices")
    CounterService counterService;

    public void exampleMethod() {
        this.counterService.increment("mycount");
    }

}
