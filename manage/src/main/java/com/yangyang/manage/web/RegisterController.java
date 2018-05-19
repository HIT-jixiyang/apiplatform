package com.yangyang.manage.web;

import com.yangyang.pojo.entity.Consumer;
import com.yangyang.pojo.entity.Sp;
import com.yangyang.pojo.mapper.ConsumerMapper;
import com.yangyang.pojo.service.RegisterService;
import com.yangyang.utils.utils.UUID;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import java.beans.PropertyVetoException;
@CrossOrigin
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
