package com.yangyang.pojo.service;

import com.yangyang.pojo.entity.RestResult;
import com.yangyang.pojo.entity.ResultStatusCode;
import com.yangyang.pojo.entity.Sp;
import com.yangyang.pojo.mapper.SpMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class LoginService {
    @Autowired
   private SpMapper spMapper;

    public  boolean existSp(Sp sp){

        //Sp s=spMapper.getSpByEmail(sp.getEmail());
        Sp s=spMapper.getSpByEmail(sp.getSp_email());
        System.out.println(s.getSp_org_id());
        System.out.println(s.toString());
        if(s!= null&&s.getSp_password().equals(sp.getSp_password())){
            System.out.println("登录成功");
            return true;
        }
        else
            return false;
    }
    public Sp findSpByEmail(String eamil){
return spMapper.getSpByEmail(eamil);
    }
    public RestResult login(String email, String password){
        Sp s=spMapper.getSpByEmail(email);
        if(s!=null&&s.getSp_password().equals(password)){
            return new RestResult(ResultStatusCode.OK.getStatuscode(),ResultStatusCode.OK.getMessage(),null);
        }
        else {
            return new RestResult(ResultStatusCode.SYSTEM_ERROR.getStatuscode(),ResultStatusCode.SYSTEM_ERROR.getMessage(),null);
        }
    }
}
