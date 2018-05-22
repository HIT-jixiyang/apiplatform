package com.yangyang.pojo.mapper;


import com.yangyang.pojo.entity.Consumer;
import com.yangyang.pojo.entity.Sp;
import com.yangyang.pojo.provider.ConsumerProvider;
import com.yangyang.pojo.provider.SpProvider;
import org.apache.ibatis.annotations.*;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@Mapper
public interface SpMapper {
    @Select("select * from service_provider")
    List<Sp> getAllSpList();

    /*
    根据id获取服务提供商
     */
    @Select("select * from service_provider where sp_email=#{email}")
    Sp getSpByEmail(@Param("email") String email);

    /*
    添加服务提供商
     */
    //@Insert("  INSERT INTO  service_provider(sp_id,sp_name,sp_org_id,sp_description,sp_tel,sp_representative,sp_representative_id,sp_email,sp_password) VALUES  (#{sp_id},#{sp_name},#{sp_org_id},#{sp_description},#{sp_tel},#{sp_representative},#{sp_representative_id},#{sp_email},#{sp_password})")
    @InsertProvider(type = SpProvider.class,method = "insertSp")
    Integer addSp(Sp sp);
    //@Update("UPDATE service_provider set sp_name=#{sp_name},sp_org_id=#{sp_org_id},sp_description=#{sp_description},sp_tel=#{sp_tel},sp_representative=#{sp_representative},sp_representative_id=#{sp_representative_id},sp_email=#{sp_email},sp_password=#{sp_password} where sp_id=#{sp_id} ")
   @UpdateProvider(type = SpProvider.class,method = "updateSpBySpExample")
    Integer updateSp(Sp sp);
    @SelectProvider(type = SpProvider.class, method = "getSpPageListBySpExample")
    public List<Sp> getSpPageListBySpExample(Integer pageNo, Integer pageSize, Sp sp, String key);
    @SelectProvider(type = SpProvider.class, method = "countPageList")
    public Integer getCountList(Sp sp, String key);
}
