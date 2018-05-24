package com.yangyang.pojo.mapper;


import com.yangyang.pojo.entity.Leaf;
import com.yangyang.pojo.provider.LeafProvider;
import org.apache.ibatis.annotations.InsertProvider;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.SelectProvider;

import java.util.List;

@Mapper
public interface LeafMapper {
@InsertProvider(type = LeafProvider.class,method = "insertLeaf")
    public Integer addLeaf(Leaf leaf);
@SelectProvider(type = LeafProvider.class,method = "getLeafListByLeafExample")
    public List<Leaf> getLeafListByLeafExample(Leaf leaf);

}
