package com.yangyang.utils.utils;

import jdk.internal.util.xml.impl.Input;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;

/**
 * @program: apiplatform
 * @description:
 * @author: JiXiYang
 * @create: 2018-05-16 10:45
 **/
public class InputStreamUtil {
    public static String InputStream2String(InputStream inputStream) throws IOException {
        ByteArrayOutputStream result = new ByteArrayOutputStream();
        byte[] buffer = new byte[1024];
        int length;
        while ((length = inputStream.read(buffer)) != -1) {
            result.write(buffer, 0, length);
        }
        return result.toString("UTF-8");
    }
}
