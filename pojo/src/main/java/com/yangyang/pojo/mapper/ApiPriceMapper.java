package com.yangyang.pojo.mapper;

import com.yangyang.pojo.entity.ApiPrice;
import com.yangyang.pojo.provider.ApiPriceProvider;
import org.apache.ibatis.annotations.*;

import java.util.List;
import java.util.Map;

@Mapper
public interface ApiPriceMapper {
    @InsertProvider(type = ApiPriceProvider.class, method = "insertApiPrice")
    public boolean addApiPrice(ApiPrice apiprice);
    @Select("select * from h2_api_price where api_id=#{api_id}")
    public List<ApiPrice> getApiPriceListByApiId(String api_id);
    @UpdateProvider(type = ApiPriceProvider.class, method = "updateApiPriceByApiPriceExample")
    public int updateApiPriceByApiPriceExample(ApiPrice apiprice);
    @Select("select * from h2_api_price ")
    public List<ApiPrice> getAllApiPriceList();
    @Delete("delete from h2_api_price where price_id=#{price_id}")
    public int deleteApiPriceByApiPriceID(String price_id);
    @Delete("delete from h2_api_price where api_id=#{api_id}")
    public int deleteApiPriceByApiID(String api_id);
    @Select("select * from h2_api_price where price_id=#{price_id}")
    public ApiPrice getApiPriceByApiPriceID(String price_id);
    @SelectProvider(type = ApiPriceProvider.class,method = "getApiPriceListByApiPriceExample")
    public List<ApiPrice> getApiPriceListByApiPriceExample(Integer pageNo, Integer pageSize, ApiPrice apiprice);
    /*
    @Select("select apiprice_id as service_id,apiprice_ip")
    public List<ZuulRouteVO> getZuulApiPriceBySp_id(String sp_id);*/
    }
