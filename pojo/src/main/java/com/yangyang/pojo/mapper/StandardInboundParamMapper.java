package com.yangyang.pojo.mapper;

import com.yangyang.pojo.entity.StandardInboundParam;
import com.yangyang.pojo.provider.StandardParamProvider;
import org.apache.ibatis.annotations.*;

import java.util.List;

/**
 * @program: apiplatform
 * @description:
 * @author: JiXiYang
 * @create: 2018-05-15 10:18
 **/
@Mapper
public interface StandardInboundParamMapper {
    @Select("SELECT * FROM h2_standard_inbound_param WHERE api_category_id=#{api_category_id}")
public List<StandardInboundParam> getStandardInboundParamListByApiCategoryID(String api_category_id);
@InsertProvider(type = StandardParamProvider.class,method = "insertStandardInboundParam")
    public Integer insertStandardInboundParam(StandardInboundParam standardInboundParam);
@Delete("delete  FROM h2_standard_inbound_param WHERE api_category_id=#{api_category_id}")
public Integer deleteStandardParamByApiCategoryID(@Param("api_category_id") String api_category_id);

}
