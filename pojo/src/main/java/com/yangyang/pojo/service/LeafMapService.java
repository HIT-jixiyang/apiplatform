package com.yangyang.pojo.service;

import com.yangyang.pojo.entity.LeafMap;
import com.yangyang.pojo.mapper.LeafMapMapper;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @program: apiplatform
 * @description:
 * @author: JiXiYang
 * @create: 2018-05-24 16:07
 **/
@Service
public class LeafMapService {
    private static final org.slf4j.Logger LOGGER = LoggerFactory.getLogger(LeafMapService.class);
    @Autowired
    LeafMapMapper leafMapMapper;

    public Boolean addMapList(List<LeafMap> leafMaps) {
        try {
            for (LeafMap leafMap : leafMaps) {
                leafMapMapper.addLeafMap(leafMap);
            }
            return true;
        } catch (Exception e) {
            LOGGER.error(e.toString());
            return false;
        }
    }

    public Boolean removeLeafMapByApi_id(String api_id) {
        try {
            leafMapMapper.removeLeafMap(api_id);
            return true;
        } catch (Exception e) {
            LOGGER.error(e.toString());
            return false;
        }
    }
}
