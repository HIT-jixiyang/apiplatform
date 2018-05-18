package com.yangyang.manage.web;

import com.yangyang.manage.component.FastDFSClient;
import com.yangyang.pojo.entity.*;
import com.yangyang.pojo.service.ApiCategoryService;
import com.yangyang.pojo.service.ApiService;
import com.yangyang.pojo.service.StandardInboundParamService;
import com.yangyang.utils.utils.ApiCategoryID;
import com.yangyang.utils.utils.ClassUtil;
import com.yangyang.utils.utils.UploadFileUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @program: apiplatform
 * @description:
 * @author: JiXiYang
 * @create: 2018-05-15 09:19
 **/
@CrossOrigin
@RestController
public class AdminController {
    @Autowired
    StandardInboundParamService standardInboundParamService;
    @Autowired
    ApiCategoryService apiCategoryService;
    @Autowired
    ApiService apiService;
    @Autowired
    FastDFSClient fastDFSClient;
    @PostMapping(value = "/admin/add-apicategory")
    public RestResult addApiCategory(@RequestBody Map<String,Object> map){

            Map<String,Object> api_category= (Map<String, Object>) map.get("api_category");
            ApiCategory apiCategory= ClassUtil.mapToClass(api_category,ApiCategory.class);
            apiCategory.setApi_category_id(ApiCategoryID.getID());
            System.out.println(apiCategory.toString());
            List<Map> paramList= (List<Map>) map.get("paramlist");
            List<StandardInboundParam> standardInboundParamList=new ArrayList<>();
            StandardInboundParam standardInboundParam=null;
            if (paramList.size()>0){
                for (Map map1 :paramList){
                    standardInboundParam=ClassUtil.mapToClass(map1,StandardInboundParam.class);
                    standardInboundParamList.add(standardInboundParam);
                }
                System.out.println(standardInboundParamList.toString());
                return  apiCategoryService.addApiCategory(apiCategory,standardInboundParamList);
            }

          return new RestResult(0,"error",null);
    }
    @PostMapping(value = "/admin/modify-apicategory")
    public RestResult modifyApiCategory(@RequestBody Map<String,Object> map){
        return  null;
    }
    //获取一页api类
    @PostMapping(value = "/admin/get-apicategorylist")
    public RestResult getApicategoryList(@RequestBody Map<String,Object> map){
        return  null;
    }
    @RequestMapping(value="/admin/uploadjar", method = RequestMethod.POST)
    public @ResponseBody
    RestResult uploadImg(@RequestParam("file") MultipartFile file, @RequestParam("api_id") String api_id,
                         HttpServletRequest request) {
        String fileName = file.getOriginalFilename();
        System.out.println(api_id);
        Map<String,Object> map=new HashMap<>();
        /*System.out.println("fileName-->" + fileName);
        System.out.println("getContentType-->" + contentType);*/
        Api api=new Api();
        api.setApi_id(api_id);
        if (!fileName.endsWith("jar")){
            map.put("info","上传文件必须是jar文件");
            return new RestResult(ResultStatusCode.SYSTEM_ERROR.getStatuscode(),ResultStatusCode.SYSTEM_ERROR.getMessage(),map);
        }
        try {
           RestResult restResult=fastDFSClient.uploadFile(file);
           api.setApi_jar_path((String) restResult.getData());
           apiService.updateApi(api);
            return restResult;
        } catch (Exception e) {
            map.put("info",e.toString());
            return new RestResult(ResultStatusCode.SYSTEM_ERROR.getStatuscode(),"上传出错",map);
            // TODO: handle exception
        }
    }
}
