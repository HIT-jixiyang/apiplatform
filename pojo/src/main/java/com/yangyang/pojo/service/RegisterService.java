package com.yangyang.pojo.service;


import com.yangyang.pojo.entity.Sp;
import com.yangyang.pojo.mapper.SpMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;

@Service
public class RegisterService {
    @Autowired
    private SpMapper spMapper;
    @Autowired
    JdbcTemplate jdbcTemplate;

    public boolean InsertSp(Sp sp) {
        System.out.println("Service收到"+sp.toString());
if(spMapper.addSp(sp)){
    return true;
}else{
    return  false;
}
    }
}
