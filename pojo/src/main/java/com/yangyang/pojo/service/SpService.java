package com.yangyang.pojo.service;

import com.yangyang.pojo.entity.Sp;
import com.yangyang.pojo.mapper.SpMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;

@Service
public class SpService {
    @Autowired
    SpMapper spMapper;
    public Boolean addSp(Sp sp){
        return  (spMapper.addSp(sp)==1)?true :false;
    }
    public Boolean updateSp(Sp sp){
        return  spMapper.updateSp(sp)==1?true:false;
    }
    public Map<String,Object> getSpList(Integer pageNo,Integer pageSize,Sp sp,String key){
        Map<String,Object> map=new HashMap<>();
        map.put("total",spMapper.getCountList(sp,key));
        map.put("data",spMapper.getSpPageListBySpExample(pageNo,pageSize,sp,key));
        return map;
    }

}
