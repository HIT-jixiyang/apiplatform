package com.yangyang.apiplatform;

import com.yangyang.apiplatform.entity.Api;
import com.yangyang.apiplatform.mapper.ApiMapper;
import com.yangyang.apiplatform.service.ApiService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;
import java.util.Map;

@RunWith(SpringRunner.class)
@SpringBootTest
public class ApiTests {
    @Autowired
    ApiMapper apiMapper;
    @Autowired
    ApiService apiService;

    @Test
    public  void ApiSearchTest(){
    List<Map> apilist=apiMapper.getApiPageListByApiExample(1,10,new Api());
        System.out.println(apilist.get(1).get("api_name"));
    }
    @Test
    public void Testxing(){
        System.out.println("/"+"add"+"/**");
    }
}
