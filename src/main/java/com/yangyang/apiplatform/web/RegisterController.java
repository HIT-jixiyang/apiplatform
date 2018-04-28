package com.yangyang.apiplatform.web;

import com.yangyang.apiplatform.entity.Consumer;
import com.yangyang.apiplatform.entity.Sp;
import com.yangyang.apiplatform.mapper.ConsumerMapper;
import com.yangyang.apiplatform.service.RegisterService;
import com.yangyang.apiplatform.utils.UUID;
import org.apache.ibatis.annotations.Param;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import java.beans.PropertyVetoException;

@RestController
public class RegisterController {
    @Autowired
    RegisterService registerService;
    @Autowired
    ConsumerMapper consumerMapper;

    public RegisterController() throws PropertyVetoException {
    }

    @RequestMapping(value = "/sp/sp_register", method = RequestMethod.POST)
    public String RegisterSp(Sp sp
                           /*     @RequestParam(value = "sp_name") String sp_name,
                               @RequestParam(value = "sp_org_id") String sp_org_id,
                               @RequestParam(value = "sp_description") String sp_description,
                               @RequestParam(value = "sp_tel") String sp_tel,
                               @RequestParam(value = "sp_representative") String sp_representative ,
                               @RequestParam(value = "sp_representative_id") String sp_representative_id ,
                               @RequestParam(value = "sp_email") String sp_email ,
                               @RequestParam(value = "sp_password") String sp_password*/
    ) {
      /* Sp sp=new Sp();
        sp.setSp_id(UUID.getUUID());
        sp.setSp_name(sp_name);
        sp.setSp_description(sp_description);
        sp.setSp_email(sp_email);
        sp.setSp_org_id(sp_org_id);
        sp.setSp_password(sp_password);
        sp.setSp_representative(sp_representative);
        sp.setSp_representative_id(sp_representative_id);
        sp.setSp_tel(sp_tel);
        System.out.println(sp.toString());*/
        System.out.println("进入注册方法");
        System.out.println(sp.toString());
        sp.setSp_id(UUID.getUUID());
        if (registerService.InsertSp(sp)) {
            return "success";
        } else {
            return "error";
        }

    }

    @RequestMapping(value = "/consumer/consumer_register", method = RequestMethod.POST)
    public String ConsumerRegister(Consumer consumer) {
        System.out.println(consumer.toString());
        consumer.setConsumer_id(UUID.getUUID());
        if (consumerMapper.addConsumer(consumer) == 1) {
            return "success";
        } else return "error";

    }

    @RequestMapping(value = "/consumer/consumer_registerpage")
    public ModelAndView consumerRegisterPage() {
        ModelAndView mv = new ModelAndView("/consumer/consumer_register");
        return mv;
    }

    @RequestMapping(value = "/sp/sp_registerpage")
    public ModelAndView spRegisterPage() {
        ModelAndView mv = new ModelAndView("/sp/sp_register");
        return mv;
    }
}
