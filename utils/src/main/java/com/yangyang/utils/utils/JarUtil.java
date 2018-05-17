package com.yangyang.utils.utils;

import com.alibaba.fastjson.JSONObject;
import com.yangyang.entity.RequestBody;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;

import java.io.File;
import java.lang.reflect.Method;
import java.net.URL;
import java.net.URLClassLoader;
import java.util.ArrayList;
import java.util.List;

/**
 * @program: parammap
 * @description:
 * @author: JiXiYang
 * @create: 2018-05-14 17:07
 **/
public class JarUtil {
    public static RequestBody getChangedRequestBodyByApiID(String api_id,RequestBody requestBody){
        String relativelyPath=System.getProperty("user.dir");
        URL[] urls = new URL[] {};
        MyClassLoader classLoader = new MyClassLoader(urls,ClassLoader.getSystemClassLoader());
        try {
            classLoader.addJar(new File("src/main/resources/jar/param-map-1.0-SNAPSHOT.jar").toURI().toURL());
            Class<?> clazz = classLoader.loadClass("com.yangyang.utils.ParamMap");
            Method method = clazz.getDeclaredMethod("inboundParam2OutBoundParam",RequestBody.class);
            Object obj=clazz.newInstance();
            MultiValueMap<String, String> header=new LinkedMultiValueMap<>();
            List<String> list1=new ArrayList<>();
            list1.add("2018-05-21");
            header.put("date",list1);
            RequestBody requestBody1= (RequestBody) method.invoke(obj,requestBody);
            classLoader.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }
    public static void main(String[] args) {
        String relativelyPath=System.getProperty("user.dir");
        URL[] urls = new URL[] {};
        MyClassLoader classLoader = new MyClassLoader(urls,ClassLoader.getSystemClassLoader());
        try {
            classLoader.addJar(new File("src/main/resources/jar/param-map-1.0-SNAPSHOT.jar").toURI().toURL());
            Class<?> clazz = classLoader.loadClass("com.yangyang.utils.ParamMap");
            Method method = clazz.getDeclaredMethod("inboundParam2OutBoundParam",RequestBody.class);
            Object obj=clazz.newInstance();
            RequestBody requestBody=new RequestBody();

            MultiValueMap<String, String> header=new LinkedMultiValueMap<>();
            List<String> list1=new ArrayList<>();
            list1.add("2018-05-21");
            header.put("date",list1);
            RequestBody requestBody1= (RequestBody) method.invoke(obj,requestBody);
            classLoader.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    static class MyClassLoader extends URLClassLoader {

        public MyClassLoader(URL[] urls) {
            super(urls);
        }

        public MyClassLoader(URL[] urls, ClassLoader parent) {
            super(urls, parent);
        }

        public void addJar(URL url) {
            this.addURL(url);
        }

    }
}
