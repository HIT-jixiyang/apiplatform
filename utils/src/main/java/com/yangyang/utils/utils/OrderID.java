package com.yangyang.utils.utils;

/**
 * @program: apiplatform
 * @description: 获得APPID的工具类
 * @author: JiXiYang
 * @create: 2018-04-21 11:46
 **/
public class OrderID {
    public static String getOrderID(){
     long timeMill=System.currentTimeMillis();
     String OrderID=timeMill+RandomStrUtil.getRandomString(10);
    return OrderID;
    }
}
