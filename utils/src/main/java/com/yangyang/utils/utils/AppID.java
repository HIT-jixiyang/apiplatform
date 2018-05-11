package com.yangyang.utils.utils;

/**
 * @program: apiplatform
 * @description: 获得APPID的工具类
 * @author: JiXiYang
 * @create: 2018-04-21 11:46
 **/
public class AppID {
    public static String getAppID(){
     long timeMill=System.currentTimeMillis();
     String AppID=timeMill+RandomStrUtil.getRandomString(7);
    return AppID;
    }
}
