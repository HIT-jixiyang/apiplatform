package com.yangyang.manage.web;

import com.yangyang.pojo.entity.Api;
import com.yangyang.pojo.entity.RestResult;
import com.yangyang.pojo.entity.ResultStatusCode;
import com.yangyang.pojo.mapper.ApiMapper;
import com.yangyang.pojo.service.ApiService;
import com.yangyang.utils.utils.UploadFileUtil;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.Map;

/**
 * @program: apiplatform
 * @description:
 * @author: JiXiYang
 * @create: 2018-05-14 20:03
 **/
@Controller
public class UploadController {
    private static final org.slf4j.Logger LOGGER = LoggerFactory.getLogger(UploadController.class);
    @Autowired
    ApiService apiService;
    //处理文件上传
    @RequestMapping(value="/upload-param-adjust-jar", method = RequestMethod.POST)
    public @ResponseBody
    RestResult uploadImg(@RequestParam("file") MultipartFile file,@RequestParam("api_id") String api_id,
                         HttpServletRequest request) {
        String contentType = file.getContentType();
        String fileName = file.getOriginalFilename();
        System.out.println(api_id);
        Map<String,Object> map=new HashMap<>();
        /*System.out.println("fileName-->" + fileName);
        System.out.println("getContentType-->" + contentType);*/
        if (!fileName.endsWith("jar")){
            map.put("info","上传文件必须是jar文件");
            return new RestResult(ResultStatusCode.SYSTEM_ERROR.getStatuscode(),ResultStatusCode.SYSTEM_ERROR.getMessage(),map);
        }
        String filePath = request.getSession().getServletContext().getRealPath("jars/");

        try {
            UploadFileUtil.uploadFile(file.getBytes(), filePath, fileName);
          } catch (Exception e) {
            map.put("info","上传出错");

            return new RestResult(ResultStatusCode.SYSTEM_ERROR.getStatuscode(),ResultStatusCode.SYSTEM_ERROR.getMessage(),map);
            // TODO: handle exception
        }
        //返回json
        map.put("info","上传成功");
        Api api=new Api();
        api.setApi_id(api_id);
        api.setApi_jar_path(filePath+fileName);
        LOGGER.info("jar路径:"+filePath+fileName);
        apiService.updateApi(api);
        return new RestResult(ResultStatusCode.OK.getStatuscode(),ResultStatusCode.OK.getMessage(),map);

    }
}
