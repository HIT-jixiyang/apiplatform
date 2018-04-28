package com.yangyang.apiplatform.mapper;

import com.yangyang.apiplatform.entity.Api;
import com.yangyang.apiplatform.provider.ApiProvider;
import org.apache.ibatis.annotations.*;

import java.util.List;
import java.util.Map;

@Mapper
public interface ApiMapper {
   @Insert("insert into api" +
            "(api_id," +
            "api_url," +
            "sp_id," +
            "api_max_in," +
            "api_enabled," +
            "api_description," +
            "api_path," +
            "api_name," +
            "api_bill_type," +
            "api_sys_price," +
            "api_method," +
            "api_return_pattern ," +
            "api_normal_return_demo," +
            " api_error_return_demo" +
            "api_timeout"+
            ") values(" +
            "#{api_id},#{api_url},#{sp_id}," +
            "#{api_max_in}," +
            "#{api_enabled},#{api_description}," +
            "#{api_path},#{api_name}," +
            "#{api_bill_type},#{api_sys_price}," +
            "#{api_method},#{api_return_pattern}" +
            ",#{api_normal_return_demo}" +
            ",#{api_error_return_demo})"+"#{api_timeout}")
    public boolean addApi(Api api);
    @Select("select * from api where sp_id=#{sp_id}")
    public List<Api> getApiListBySpId(String sp_id);
    @Update("update api set api_ip=#{api_ip},api_port=#{api_port},api_max_in=#{api_max_in},api_enabled=#{api_enabled},api_description=#{api_description},api_path=#{api_path} ,api_name=#{api_name} ,api_bill_type=#{api_bill_type},api_sys_price=#{api_sys_type},api_timeout=#{api_timeout} where api_id=#{api_id}")
    public boolean updateApiByApiID(Api api);
    @Select("select * from api ")
    public List<Api> getAllApiList();
    @Delete("delete from api where api_id=#{api_id}")
    public int deleteApiByApiID(String api_id);
    // 获取api分页列表
    @SelectProvider(type = ApiProvider.class, method = "getApiPageListByApiExample")
    List<Map> getApiPageListByApiExample(Integer pageNo, Integer pageSize, Api api);

    // 获取api数
    @SelectProvider(type = ApiProvider.class, method = "countPageList")
    Integer countPageList(Api api);
    @Select("select * from api where api_id=#{api_id}")
    public Api getApiByApiID(String api_id);
    /*
    @Select("select api_id as service_id,api_ip")
    public List<ZuulRouteVO> getZuulApiBySp_id(String sp_id);*/
}
