package com.yangyang.pojo.service;

import com.yangyang.pojo.entity.StandardInboundParam;
import com.yangyang.pojo.mapper.StandardInboundParamMapper;
import com.yangyang.utils.utils.UUID;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * @program: apiplatform
 * @description:
 * @author: JiXiYang
 * @create: 2018-05-15 10:45
 **/
@Service
public class StandardInboundParamService {
    @Autowired
    StandardInboundParamMapper standardInboundParamMapper;
    @Transactional
    public boolean ModifyStandardParamList(String api_category_id,List<StandardInboundParam> standardInboundParams){
    try {
        standardInboundParamMapper.deleteStandardParamByApiCategoryID(api_category_id);
        for (StandardInboundParam param :standardInboundParams){
            param.setApi_category_id(api_category_id);
            param.setStandard_inbound_param_id(UUID.getUUID());
            standardInboundParamMapper.insertStandardInboundParam(param);
        }
    }catch (Exception e){
        return false;
    }
   return true;
}
public List<StandardInboundParam> getStandardParamListByApiCategoryID(String api_category_id){
        List<StandardInboundParam> paramList=standardInboundParamMapper.getStandardInboundParamListByApiCategoryID(api_category_id);
        if (paramList.size()>=1){
            return paramList;
        }
        else return null;
}
}
