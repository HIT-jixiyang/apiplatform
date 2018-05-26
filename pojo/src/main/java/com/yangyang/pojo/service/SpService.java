package com.yangyang.pojo.service;

import com.yangyang.pojo.entity.Sp;
import com.yangyang.pojo.mapper.SpMapper;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;

@Service
public class SpService {
    private static final org.slf4j.Logger LOGGER = LoggerFactory.getLogger(SpService.class);
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
public Sp getSpBySpID(String sp_id){
        Sp sp=new Sp();
        sp.setSp_id(sp_id);
        return  spMapper.getSpPageListBySpExample(1,1,sp,null).get(0);
}
public Sp spLogin(String sp_email,String password){
    try {
        Sp sp=spMapper.getSpByEmail(sp_email);
        if(sp==null){
            return  null;
        }
        if(!sp.getSp_password().equals(password)){
            return null;
        }
        return  sp;
    }catch (Exception e){
        LOGGER.error(e.getMessage());
        return null;
    }

}
}
