package com.yangyang.apiplatform.utils;

import com.alibaba.fastjson.JSONObject;

import java.util.Map;

/**
 * @Author: ny
 * @Date: Created in 10:03 2018/4/7 0007
 */
public class ClassUtil {
    public static <T> T mapToClass(Map map, Class<T> clazz) {
        if (map == null) return null;
        T t;
        try {
            t = JSONObject.parseObject(JSONObject.toJSONString(map), clazz);
        } catch (Exception e) {
            return null;
        }
        return t;
    }

}
