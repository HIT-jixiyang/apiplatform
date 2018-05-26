package com.yangyang.manage.web;

import com.yangyang.pojo.entity.Consumer;
import com.yangyang.pojo.entity.RestResult;
import com.yangyang.pojo.entity.Sp;
import com.yangyang.pojo.mapper.ConsumerMapper;
import com.yangyang.pojo.service.ConsumerService;
import com.yangyang.pojo.service.RegisterService;
import com.yangyang.pojo.service.SpService;
import com.yangyang.utils.utils.ClassUtil;
import com.yangyang.utils.utils.RandomStrUtil;
import com.yangyang.utils.utils.UUID;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import java.beans.PropertyVetoException;
import java.util.Map;

@CrossOrigin
@RestController
public class RegisterAndLoginController {
    @Autowired
    RegisterService registerService;
    @Autowired
    ConsumerService consumerService;
    @Autowired
    SpService spService;
    private static final org.slf4j.Logger LOGGER = LoggerFactory.getLogger(RegisterAndLoginController.class);
    public RegisterAndLoginController() throws PropertyVetoException {
    }

    @RequestMapping(value = "/sp_register", method = RequestMethod.POST)
    public RestResult RegisterSp(@RequestBody Map map) {
        Sp sp= ClassUtil.mapToClass(map,Sp.class);
        if(!checkSp(sp)){
           return new RestResult(0,"缺少必要的数据","");
        }
        LOGGER.info(sp.toString());
        sp.setSp_id(System.currentTimeMillis()+ RandomStrUtil.getRandomString(7));
       sp.setSp_state(0);
        try {
            if (spService.addSp(sp)) {
                return new RestResult(1,"OK","");
            } else {
                return new RestResult(0,"ERROR","新增记录失败");
            }

        }catch (Exception e){
            LOGGER.error(e.getMessage());
             return new RestResult(0,"ERROR",e.toString());
        }
    }
    @RequestMapping(value = "/consumer_register")
public  RestResult RegisterConsumer(@RequestBody Map map){
Consumer consumer=ClassUtil.mapToClass(map,Consumer.class);
if(!checkConsumer(consumer)){
    return new RestResult( 0,"缺少必要的数据","");
}
consumer.setConsumer_id(System.currentTimeMillis()+RandomStrUtil.getRandomString(7));
consumer.setConsumer_state(0);
try {
    if (consumerService.addConsumer(consumer)){
        return new RestResult(1,"OK",null);
    }else {
        return new RestResult(0,"数据库错误",null);
    }

}catch (Exception e){
    LOGGER.error(e.toString());
    return  new RestResult(0,"登记失败",e.toString());
}
}
public  Boolean checkSp(Sp sp){
    if(sp.getSp_email()==null||sp.getSp_description()==null||sp.getSp_name()==null||
            sp.getSp_password()==null||sp.getSp_tel()==null||sp.getSp_representative()==null||
            sp.getSp_representative_id()==null){
        return false;
    }else {
        return true;
    }
}
public Boolean checkConsumer(Consumer consumer){
    if (consumer.getConsumer_card_id()==null||consumer.getConsumer_email()==null||consumer.getConsumer_name()==null||
            consumer.getConsumer_tel()==null||consumer.getConsumer_type()==null){
        return false;
    }else {
        return true;
    }
}
@RequestMapping(value = "/splogin")
public RestResult Login(@RequestBody Map map){
    String emil= (String) map.get("email");
    String password= (String) map.get("password");
Integer role= (Integer) map.get("role");
if (role==1){
    try {
        Sp sp= spService.spLogin(emil,password);
        if (sp!=null){
            return new RestResult(1,"登录成功",sp);
        }else {
            return new RestResult(0,"登录失败",null);
        }
         }catch (Exception e){
        LOGGER.error(e.toString());
        return new RestResult(0,"登录失败",e.toString());
    }
    }if(role==0){
        try {
            Consumer consumer= consumerService.ConsumerLogin(emil,password);
            if (consumer!=null){
                return new RestResult(1,"登录成功",consumer);
            }else {
                return new RestResult(0,"登录失败",null);
            }
        }catch (Exception e){
            LOGGER.error(e.toString());
            return new RestResult(0,"登录失败",e.toString());
        }
    }
   return new RestResult(0,"角色码不对",null);
}



}
