package com.yangyang.utils.utils;/*
package com.yangyang.apiplatform.utils;

import com.alibaba.fastjson.JSONObject;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.text.CharacterIterator;
import java.text.StringCharacterIterator;
import java.util.*;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
*/
/**
 * @program: apiplatform
 * @description: Json的工具类
 * @author: JiXiYang
 * @create: 2018-05-05 20:43
 **//*

public class JsonUtils {

    public static String getKeys(org.json.JSONObject test) throws JSONException{

        String result = null;
        testJsonCompare t = new testJsonCompare();
        Iterator keys = test.keys();
        while(keys.hasNext()){

            try{

                String key = keys.next().toString();
                String value = test.optString(key);

                int i = t.testIsArrayORObject(value);

                if(result == null || result.equals("")){
                    if(i == 0){

                        result = key + ",";
                        System.out.println("i=0 | key="+key+"| result="+result);


                    }else if( i == 1){

                        result = key + ",";
                        System.out.println("i=1 | key="+key+"| result="+result);
                        result = getKeys(new JSONObject(value))+",";
                    }else if( i == 2){

                        result = key + ",";
                        System.out.println("i=2 | key="+key+"| result="+result);
                        JSONArray arrays = new JSONArray(value);
                        for(int k =0;k<arrays.length();k++){
                            JSONObject array = new JSONObject(arrays.get(k));
                            result = getKeys(array) + ",";
                        }
                    }

                }else{
                    if(i == 0){

                        result = result + key + ",";
                        System.out.println("i=0 | key="+key+"| result="+result);


                    }else if( i == 1){

                        result = result + key + ",";
                        System.out.println("i=1 | key="+key+"| result="+result);
                        result = result + getKeys(new JSONObject(value));
                    }else if( i == 2){
                        result = result + key + ",";
                        System.out.println("i=2 | key="+key+"| result="+result);
                        JSONArray arrays = new JSONArray(value);
                        for(int k =0;k<arrays.length();k++){
                            JSONObject array = new JSONObject(arrays.get(k));
                            result = result + getKeys(array) + ",";
                        }
                    }
                }


            }catch(JSONException e){
                e.printStackTrace();
            }
        }


        return result;
    }
    public static void main(String[] args) {
        String s ="{\"teacherName\":\"crystall\",\"teacherAge\":27,\"course\":{\"courseName\":\"english\",\"code\":1270},\"students\":[{\"studentName\":\"lily\",\"studentAge\":12},{\"studentName\":\"lucy\",\"studentAge\":15}]}";
        for (Object obj: set) {
            if(obj instanceof Integer){
                int aa= (Integer)obj;
                System.out.println(aa);
            }else if(obj instanceof String){
                String aa = (String)obj;
                System.out.println(aa);
            }
        }
    }
}
*/
