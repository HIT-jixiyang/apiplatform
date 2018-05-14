package com.yangyang.utils.utils;

import java.io.File;
import java.io.FileOutputStream;

/**
 * @program: testuploadfile
 * @description:
 * @author: JiXiYang
 * @create: 2018-05-14 19:51
 **/
public class UploadFileUtil {
    public static void uploadFile(byte[] file, String filePath, String fileName) throws Exception {
        File targetFile = new File(filePath);
        System.out.println(filePath);
        if(!targetFile.exists()){
            targetFile.mkdirs();
        }
        FileOutputStream out = new FileOutputStream(filePath+fileName);
        out.write(file);
        out.flush();
        out.close();
    }
}
