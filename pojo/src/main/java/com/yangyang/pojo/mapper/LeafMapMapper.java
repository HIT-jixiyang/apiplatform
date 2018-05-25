package com.yangyang.pojo.mapper;

import com.yangyang.pojo.entity.Leaf;
import com.yangyang.pojo.entity.LeafMap;
import com.yangyang.pojo.provider.LeafMapProvider;
import com.yangyang.pojo.provider.LeafProvider;
import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.InsertProvider;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.SelectProvider;

import java.util.List;

@Mapper
public interface LeafMapMapper {
    @InsertProvider(type = LeafMapProvider.class,method = "insertLeafMap")
    public Integer addLeafMap(LeafMap leafmap);
    @SelectProvider(type = LeafMapProvider.class,method = "getLeafMapListByLeafMapExample")
    public List<LeafMap> getLeafMapListByLeafMapExample(LeafMap leafMap);
    @Delete("delete from leafmap where api_id=#{api_id}")
    public Integer removeLeafMap(String api_id);

}
