package com.yangyang.utils.utils;

/**
 * @program: apiplatform
 * @description:
 * @author: JiXiYang
 * @create: 2018-05-17 20:56
 **/
public class ApiCategoryID {
    public static String getID(){
        return System.currentTimeMillis()+RandomStrUtil.getRandomString(4);
    }
}
