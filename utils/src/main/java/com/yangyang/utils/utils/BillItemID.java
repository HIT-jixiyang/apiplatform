package com.yangyang.utils.utils;

/**
 * @program: apiplatform
 * @description: 获得APPID的工具类
 * @author: JiXiYang
 * @create: 2018-04-21 11:46
 **/
public class BillItemID {
    public static String getBillItemID(){
     long timeMill=System.currentTimeMillis();
     String BillItemID=timeMill+RandomStrUtil.getRandomString(10);
    return BillItemID;
    }
}
