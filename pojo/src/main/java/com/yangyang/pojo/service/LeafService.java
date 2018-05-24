package com.yangyang.pojo.service;

import com.yangyang.pojo.entity.Leaf;
import com.yangyang.pojo.mapper.LeafMapper;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.MultiValueMap;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

@Service
public class LeafService {
    private static final org.slf4j.Logger LOGGER = LoggerFactory.getLogger(LeafService.class);
   @Autowired
   LeafMapper leafMapper;
   @Autowired
   JsonMapService jsonMapService;
    public Boolean addLeafList(List<Leaf> leafList){
       try {
           for (Leaf leaf :leafList){
               leafMapper.addLeaf(leaf);
           }
           return true;
       }catch (Exception e){
           LOGGER.error(e.toString());
           return false;
       }
    }
    public Boolean addLeaf(Leaf leaf){
        try {
                leafMapper.addLeaf(leaf);
            return true;
        }catch (Exception e){
            LOGGER.error(e.toString());
            return false;
        }
    }
    //根据json字符串，api类的id往数据库插入标准json的叶子结点信息
    public Boolean addStandardLeafInfosByJsonExample(String json,String api_category_id){
       try {
           MultiValueMap<Integer,Leaf> integerLeafMultiValueMap=jsonMapService.getStandardLeafInfos(api_category_id,json);
           Set<Integer> set=integerLeafMultiValueMap.keySet();
           List<Leaf> leafList=new ArrayList<>();
           for (Integer key :set){
               leafList.add(integerLeafMultiValueMap.get(key).get(0));
           }
           addLeafList(leafList);
           return true;
       }catch (Exception e){
           LOGGER.error(e.toString());
           return false;
       }
    }

    public Boolean addOriginLeafInfosByJsonExample(String json,String api_id){
        try {
            MultiValueMap<Integer,Leaf> integerLeafMultiValueMap=jsonMapService.getOriginLeafInfos(api_id,json);
            Set<Integer> set=integerLeafMultiValueMap.keySet();
            List<Leaf> leafList=new ArrayList<>();
            for (Integer key :set){
                leafList.add(integerLeafMultiValueMap.get(key).get(0));
            }
            addLeafList(leafList);
            return true;
        }catch (Exception e){
            LOGGER.error(e.toString());
            return false;
        }
    }
    public List<Leaf> getLeafListByLeafExample(Leaf leaf){
        try {
            return leafMapper.getLeafListByLeafExample(leaf);
        }catch (Exception e){
            LOGGER.error(e.toString());
            return null;
        }

    }
}
