package com.yangyang.pojo.mapper;




import com.yangyang.pojo.entity.Sp;
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
  @Insert("  INSERT INTO  service_provider(sp_id,sp_name,sp_org_id,sp_description,sp_tel,sp_representative,sp_representative_id,sp_email,sp_password) VALUES  (#{sp_id},#{sp_name},#{sp_org_id},#{sp_description},#{sp_tel},#{sp_representative},#{sp_representative_id},#{sp_email},#{sp_password})")
  boolean addSp(Sp sp);
  @Update("UPDATE service_provider set sp_name=#{sp_name},sp_org_id=#{sp_org_id},sp_description=#{sp_description},sp_tel=#{sp_tel},sp_representative=#{sp_representative},sp_representative_id=#{sp_representative_id},sp_email=#{sp_email},sp_password=#{sp_password} where sp_id=#{sp_id} ")
    boolean updateSp(@Param("sp_name") String sp_name, @Param("sp_org_id") String org_id, @Param("sp_description") String description
          , @Param("sp_tel") String tel, @Param("sp_representative") String representative, @Param("sp_representative_id") String sp_representative_id,
                     @Param("sp_email") String email, @Param("sp_password") String password, @Param("sp_id") String sp_id);
}
