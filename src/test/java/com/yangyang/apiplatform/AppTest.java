package com.yangyang.apiplatform;

import com.yangyang.apiplatform.entity.App;
import com.yangyang.apiplatform.mapper.AppMapper;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

/**
 * @program: apiplatform
 * @description: 测试AppDao
 * @author: JiXiYang
 * @create: 2018-04-15 21:23
 **/
@RunWith(SpringRunner.class)
@SpringBootTest
public class AppTest {
@Autowired
    AppMapper appMapper;
@Test
public void testAdd(){
    App app=new App();
    app.setApp_description("烤面筋");
    app.setApp_name("烤面筋");
    app.setApp_secret("absudbaskba");
    app.setConsumer_id("11111111111111111");
    appMapper.addApp(app);
}
}
