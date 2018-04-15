package com.yangyang.apiplatform.mapper;

import com.yangyang.apiplatform.entity.Api;
import com.yangyang.apiplatform.entity.ApiRequestParam;
import com.yangyang.apiplatform.entity.ZuulRouteVO;
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
            "api_token," +
            "api_max_in," +
            "api_enabled," +
            "api_description," +
            "api_strip_prefix," +
            "api_retryable," +
            "api_path," +
            "api_name," +
            "api_bill_type," +
            "api_sys_price," +
            "api_method," +
            "api_return_pattern ," +
            "api_normal_return_demo," +
            " api_error_return_demo" +
            ") values(" +
            "#{api_id},#{api_url},#{sp_id}," +
            "#{api_token},#{api_max_in}," +
            "#{api_enabled},#{api_description}," +
            "#{api_strip_prefix},#{api_retryable}," +
            "#{api_path},#{api_name}," +
            "#{api_bill_type},#{api_sys_price}," +
            "#{api_method},#{api_return_pattern}" +
            ",#{api_normal_return_demo}" +
            ",#{api_error_return_demo})")
    public boolean addApi(@Param("api_id") String api_id,
                          @Param("api_url") String api_url,
                          @Param("sp_id") String sp_id,
                        @Param("api_token") String api_token,
                          @Param("api_max_in") Integer api_max_in,
                          @Param("api_enabled") Integer api_enabled,
                          @Param("api_description") String api_description,
                          @Param("api_strip_prefix") Integer api_strip_prefix,
                          @Param("api_retryable") Integer api_retryable,
                          @Param("api_path") String api_path,
                          @Param("api_name") String api_name,
                          @Param("api_bill_type") Integer api_bill_type,
                          @Param("api_sys_price") Float api_sys_price,
                          @Param("api_method") Integer api_method,
                          @Param("api_return_pattern") String api_return_pattern,
                          @Param("api_normal_return_demo") String api_normal_return_demo,
                          @Param("api_error_return_demo") String api_error_return_demo
    );
    @Select("select * from api where sp_id=#{sp_id}")
    public List<Api> getApiListBySpId(String sp_id);
    @Update("update api set api_ip=#{api_ip},api_port=#{api_port},api_token=#{api_token},api_prefix=#{api_prefix},api_max_in=#{api_max_in},api_enabled=#{api_enabled},api_description=#{api_description},api_strip_prefix=#{api_strip_prefix},api_retryable=#{api_retryable},api_path=#{api_path} ,api_name=#{api_name} ,api_bill_type=#{api_bill_type},api_sys_price=#{api_sys_type} where api_id=#{api_id}")
    public boolean updateApi(Api api);
    @Select("select * from api ")
    public List<Api> getAllApiList();


    // 获取api分页列表
    @SelectProvider(type = ApiProvider.class, method = "getApiPageList")
    List<Map> getApiPageList(Integer pageNo, Integer pageSize, Api api);

    // 获取api数
    @SelectProvider(type = ApiProvider.class, method = "countPageList")
    Integer countPageList(Api api);
    /*
    @Select("select api_id as service_id,api_ip")
    public List<ZuulRouteVO> getZuulApiBySp_id(String sp_id);*/
}
