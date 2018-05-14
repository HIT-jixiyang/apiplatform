package com.yangyang.utils.utils;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * @program: apiplatform
 * @description:
 * @author: JiXiYang
 * @create: 2018-05-14 15:56
 **/
public class DateUtil {
    public static String DateFormatUtil(Long timeStamp){
        SimpleDateFormat sdf=new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");//这个是你要转成后的时间的格式
        String sd = sdf.format(new Date(timeStamp));   // 时间戳转换成时间
       return sd;
    }
}
