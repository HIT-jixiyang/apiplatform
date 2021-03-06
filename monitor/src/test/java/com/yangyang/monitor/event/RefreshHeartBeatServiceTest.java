package com.yangyang.monitor.event;

import com.yangyang.monitor.utils.HTTPUtils;
import com.yangyang.pojo.entity.Api;
import com.yangyang.pojo.mapper.ApiMapper;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.io.IOException;
import java.util.List;

@RunWith(SpringRunner.class)
@SpringBootTest
public class RefreshHeartBeatServiceTest {
    @Autowired
    RefreshHeartBeatService refreshHeartBeatService;
    @Autowired
    ApiMapper apiMapper;
    @Test
    public void refreshHeartBearService() throws Exception {
        List<Api> apiList= apiMapper.getApiHeartList();
        System.out.println(apiList);
        refreshHeartBeatService.RefreshHeartBearService();
        apiList=apiMapper.getApiHeartList();
        System.out.println(apiList);

    }
    @Test
    public  void testhttp() throws IOException {
        System.out.println(HTTPUtils.doget("http://www.hitwh.edu"));
    }

}