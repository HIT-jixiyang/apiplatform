package com.yangyang.utils.utils;

import org.dom4j.DocumentException;
import org.dom4j.DocumentHelper;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class XmlUtil {

    public static Map<Integer,String> isXml(String xmlStr){
        Map<Integer, String> map=new HashMap<>();
        try{
            org.dom4j.Document document = DocumentHelper.parseText(xmlStr);

        }
     catch (DocumentException e) {
            e.printStackTrace();
        map.put(0,e.toString());
        return map;
        }
        map.put(1,"校验成功");
        return map;
    }
}
