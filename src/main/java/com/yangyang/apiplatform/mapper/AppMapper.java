package com.yangyang.apiplatform.mapper;

import com.yangyang.apiplatform.entity.Api;
import com.yangyang.apiplatform.entity.App;
import com.yangyang.apiplatform.provider.ApiProvider;
import com.yangyang.apiplatform.provider.AppProvider;
import org.apache.ibatis.annotations.*;

import java.math.BigInteger;
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
@Insert("insert into app(app_secret,app_description,app_name,consumer_id) values(#{app_secret},#{app_description},#{app_name},#{consumer_id})")
@Options(useGeneratedKeys =true,keyProperty = "app_id")
public int addApp(App app);
@Select("select * from app where consumer_id=#{consumer_id} ")
    public List<App> getAppListByConsumerID(String Consumer_id);
@Select("select * from app where app_id=#{app_id}")
    public App getAppByAppID(BigInteger app_id);

    // 获取app分页列表
    @SelectProvider(type = AppProvider.class, method = "getAppPageList")
    List<Map> getAppPageList(Integer pageNo, Integer pageSize, App app);

    // 获取api数
    @SelectProvider(type = AppProvider.class, method = "countPageList")
    Integer countPageList(App app);
}
