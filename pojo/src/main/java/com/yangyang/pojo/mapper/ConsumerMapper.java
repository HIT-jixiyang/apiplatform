package com.yangyang.pojo.mapper;


import com.yangyang.pojo.entity.Consumer;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Component;

@Component
@Mapper
public interface ConsumerMapper {
    @Select("select * from consumer where consumer_email=#{email}")
public Consumer getConsumerByEmail(String email);
    @Select("select * from consumer where consumer_id=#{id}")
    public Consumer getConsumerById(String id);
    @Insert("insert into consumer(consumer_id,consumer_email,consumer_password,consumer_name,consumer_tel) values(#{consumer_id},#{consumer_email},#{consumer_password},#{consumer_name},#{consumer_tel})")
    public int addConsumer(Consumer consumer);
}
