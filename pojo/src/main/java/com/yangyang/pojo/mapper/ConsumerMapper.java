package com.yangyang.pojo.mapper;


import com.yangyang.pojo.entity.Consumer;
import com.yangyang.pojo.provider.ConsumerProvider;
import org.apache.ibatis.annotations.*;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@Mapper
public interface ConsumerMapper {
    @Select("select * from consumer where consumer_email=#{email}")
    public Consumer getConsumerByEmail(String email);

    @Select("select * from consumer where consumer_id=#{id}")
    public Consumer getConsumerById(String id);

    @InsertProvider(type = ConsumerProvider.class, method = "insertConsumer")
    public Integer addConsumer(Consumer consumer);

    @UpdateProvider(type = ConsumerProvider.class, method = "updateConsumerByConsumerExample")
    public int updateConsumerByConsumerExample(Consumer consumer);

    @Delete("delete from consumer where consumer_id=#{consumer_id}")
    public int deleteConsumerByConsumerID(String consumer_id);

    @SelectProvider(type = ConsumerProvider.class, method = "getConsumerPageListByConsumerExample")
    public List<Consumer> getConsumerPageListByConsumerExample(Integer pageNo, Integer pageSize, Consumer consumer, String key);

    @SelectProvider(type = ConsumerProvider.class, method = "countPageList")
    public Integer getCountList(Consumer consumer, String key);
}
