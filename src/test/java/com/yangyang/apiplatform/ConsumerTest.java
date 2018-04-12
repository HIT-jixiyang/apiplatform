package com.yangyang.apiplatform;

import com.yangyang.apiplatform.entity.Consumer;
import com.yangyang.apiplatform.mapper.ConsumerMapper;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest
public class ConsumerTest {
    @Autowired
    ConsumerMapper consumerMapper;
    @Test
    public void testGetConsumerByEmail(){
String email="1044456468@qq.com";
Consumer consumer=consumerMapper.getConsumerByEmail(email);
        System.out.println(consumer.toString());
    }
}
