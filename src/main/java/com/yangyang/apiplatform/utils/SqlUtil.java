package com.yangyang.apiplatform.utils;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.List;

/**
 * @Author: ny
 * @Date: Created in 9:33 2018/4/7 0007
 */
public class SqlUtil {
    public static List<String[]> getNotNullField(Object obj) throws IllegalAccessException {
        Class clazz = obj.getClass();
        Field[] fileds = clazz.getDeclaredFields();
        List<String[]> res = new ArrayList<>();
        for(int i = 0, len = fileds.length; i < len; i++){
            Field field = fileds[i];
            field.setAccessible(true);
            Object val = field.get(obj);
            if(val != null) {
                String[] temp = new String[2];
                temp[0] = "`" + field.getName() + "`";
                temp[1] = field.get(obj).toString();
                if(val instanceof String){
                    temp[1] = "'"+temp[1]+"'";
                }
                res.add(temp);
            }
        }
        return res;
    }
}
