package com.yangyang.pojo.mapper;

import com.yangyang.pojo.entity.Api;
import org.apache.ibatis.annotations.*;
import com.yangyang.pojo.provider.ApiProvider;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Map;

@Mapper
public interface ApiMapper {
    @InsertProvider(type = ApiProvider.class, method = "insertApi")
    public boolean addApi(Api api);
    @Select("select a.*,b.price from api a,api_price b where a.api_id=b.api_id and b.price_type=2")
    public List<Map> getAllApiAndApiPriceList();
    @Select("select * from api where sp_id=#{sp_id}")
    public List<Api> getApiListBySpId(String sp_id);

    //@Update("update api set api_ip=#{api_ip},api_port=#{api_port},api_max_in=#{api_max_in},api_enabled=#{api_enabled},api_description=#{api_description},api_path=#{api_path} ,api_name=#{api_name} ,api_bill_type=#{api_bill_type},api_sys_price=#{api_sys_type},api_timeout=#{api_timeout} where api_id=#{api_id}")
    @UpdateProvider(type = ApiProvider.class, method = "updateApiByApiExample")
    public int updateApiByApiExample(Api api);

    @Select("select * from api ")
    public List<Api> getAllApiList();

    @Delete("delete from api where api_id=#{api_id}")
    public int deleteApiByApiID(String api_id);

    // 获取api分页列表
    @SelectProvider(type = ApiProvider.class, method = "getApiPageListByApiExample")
    List<Map> getApiPageListByApiExample(Integer pageNo, Integer pageSize, Api api,String key);

    @SelectProvider(type = ApiProvider.class, method = "getApiAndPriceListByApiExample")
    List<Map> getApiAndPriceListByApiExample(Api api);

    // 获取api数
    @SelectProvider(type = ApiProvider.class, method = "countPageList")
    Integer countPageList(Api api,String key);

    @Select("select * from api where api_id=#{api_id}")
    public Api getApiByApiID(String api_id);
    @SelectProvider(type = ApiProvider.class,method = "getApiListByApiExample")
    public List<Api> getApiListByApiExample(Integer pageNo,Integer pageSize,Api api);
    /*
    @Select("select api_id as service_id,api_ip")
    public List<ZuulRouteVO> getZuulApiBySp_id(String sp_id);*/
    @Select("select api_id,api_url,api_token,api_enabled ,api_method from api")
    public List<Api> getApiHeartList();
    //扫描完心跳，更新statement
    @Update("update api set api_enabled=#{api_enabled} where api_id=#{api_id}")
    public boolean updatestatement(@Param("api_id") String api_id,@Param("api_enabled") Integer api_enabled);
    @Select("SELECT * FROM api WHERE api_category_id=#{api_category_id} ORDER BY api_cost_algorithm_score desc LIMIT 0,#{size};")
    public List<Api> getLowestCostApiList(@Param(value = "api_category_id")String api_category_id,@Param(value = "size") Integer size);
    @Select("SELECT * FROM api WHERE api_category_id=#{api_category_id} ORDER BY api_stable_algorithm_score desc LIMIT 0,#{size};")
    public List<Api> getMostStableApiList(@Param(value = "api_category_id")String api_category_id,@Param(value = "size") Integer size);
    @Select("SELECT * FROM api WHERE api_category_id=#{api_category_id} ORDER BY api_time_algorithm_score desc LIMIT 0,#{size};")
    public List<Api> getMostFastApiList(@Param(value = "api_category_id")String api_category_id,@Param(value = "size") Integer size);
    @Select("SELECT avg(api_average_response_time) FROM  api WHERE api_category_id=#{api_category_id}")
    public Float getAverageResponseTimeByCategoryID(@Param(value = "api_category_id") String category_id);
//@Select("")
    @Select("SELECT AVG(b.price/b.content ) FROM api a,api_price b WHERE a.api_id=b.api_id and b.price_type=\"2\" AND a.api_category_id=#{api_category_id};")
    public Float getAverageCostByCategoryID(@Param(value = "api_category_id") String api_category_id);
@Select("SELECT AVG(api_success_response_ratio) FROM  api WHERE api_category_id=#{api_category_id}")
    public Float getAverageSuccessRatioByCategoryID(@Param(value = "api_category_id") String api_category_id);
}
