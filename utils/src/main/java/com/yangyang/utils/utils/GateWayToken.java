package com.yangyang.utils.utils;

/**
 * @program: apiplatform
 * @description: 获得网关token
 * @author: JiXiYang
 * @create: 2018-04-20 10:47
 **/

public class GateWayToken {
    /*
    传入appid与
    appsecret以及当前时间戳获得一个唯一的密钥
     */
    public static String getGateWayToken(String  app_id,String app_secret,long timemills){
        String token=MD5.getMD5(app_id+"hit"+app_secret+"hit"+timemills);
        return token;
    }

    public static void main(String[] args) {
        System.out.println(getGateWayToken("4","ZPdNWfNoyxiEo7cO",1234567));
    }
}
