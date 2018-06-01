package com.yangyang.pojo.mapper;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import java.util.Map;

@Mapper
public interface AdminMapper {
    @Select("select * from h2_admin where admin_name=#{admin_name}")
    public Map getAdminByAdmin_name(String admin_name);

}
