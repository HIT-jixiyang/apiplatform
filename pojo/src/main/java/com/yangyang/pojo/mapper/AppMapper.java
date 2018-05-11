package com.yangyang.pojo.mapper;



import com.yangyang.pojo.entity.App;
import com.yangyang.pojo.provider.AppProvider;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.SelectProvider;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Map;

/**
 * @program: apiplatform
 * @description: App映射数据库Dao操作
 * @author: JiXiYang
 * @create: 2018-04-15 20:39
 **/
@Mapper
public interface AppMapper {
@Insert("insert into app(app_id,app_secret,app_description,app_name,consumer_id) values(#{app_id},#{app_secret},#{app_description},#{app_name},#{consumer_id})")
public int addApp(App app);
@Select("select * from app where consumer_id=#{consumer_id} ")
    public List<App> getAppListByConsumerID(String Consumer_id);
@Select("select * from app where app_id=#{app_id}")
    public App getAppByAppID(String app_id);

    // 获取app分页列表
    @SelectProvider(type = AppProvider.class, method = "getAppPageList")
    List<Map> getAppPageList(Integer pageNo, Integer pageSize, App app);

    // 获取api数
    @SelectProvider(type = AppProvider.class, method = "countPageList")
    Integer countPageList(App app);
}
