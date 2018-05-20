package com.yangyang.manage.web;

import com.yangyang.manage.component.FastDFSClient;
import com.yangyang.pojo.entity.*;
import com.yangyang.pojo.service.ApiCategoryService;
import com.yangyang.pojo.service.ApiService;
import com.yangyang.pojo.service.StandardInboundParamService;
import com.yangyang.utils.XmlUtil;
import com.yangyang.utils.utils.ApiCategoryID;
import com.yangyang.utils.utils.ClassUtil;
import com.yangyang.utils.utils.UUID;
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

    @PostMapping(value = "/admin/test-param-xml")
    public RestResult isParamXml(@RequestBody Map map){
        String param_xml= (String) map.get("param_xml");
        return XmlUtil.getHeadersAndQuerysFromXml(param_xml);
    }
    @PostMapping(value = "/admin/add-apicategory")
    public RestResult addApiCategory(@RequestBody Map<String,Object> map){
            Map<String,Object> api_category= (Map<String, Object>) map.get("api_category");
            ApiCategory apiCategory= ClassUtil.mapToClass(api_category,ApiCategory.class);
            apiCategory.setApi_category_id(ApiCategoryID.getID());
            System.out.println(apiCategory.toString());
            String param_xml= (String) map.get("param_xml");
            String body= (String) map.get("body_param");
StandardInboundParam param=new StandardInboundParam();
param.setStandard_inbound_param_id(UUID.getUUID());
param.setApi_category_id(apiCategory.getApi_category_id());
if (body!=null)
param.setStandard_inbound_param_ismust(1);
else
    param.setStandard_inbound_param_ismust(0);
     param.setStandard_inbound_param_value_demo(body);
     param.setStandard_inbound_param_type("String");
     param.setStandard_inbound_param_desc("");
     param.setStandard_inbound_param_position(2);
     param.setStandard_inbound_param_key("body");
       try {
           RestResult restResult= XmlUtil.getHeadersAndQuerysFromXml(param_xml);
           List<StandardInboundParam> list= (List<StandardInboundParam>) restResult.getData();
           List<StandardInboundParam> standardInboundParamList=new ArrayList<>();
           if (list.size()>0){
               for(StandardInboundParam param1:list){
            param1.setApi_category_id(apiCategory.getApi_category_id());
            param1.setStandard_inbound_param_id(UUID.getUUID());
               }
           }
           return  apiCategoryService.addApiCategory(apiCategory,list);
       }
       catch (Exception e){
           return new RestResult(0,"error",null);
       }
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
    /*
    * 管理员更新API状态，传入verify为0表示将api状态置为未审核
    * verify为1表示已经审核
    * 传入adapt为0表示将api状态置为为适配
    * 传入1则表示已经适配
    * */
    @RequestMapping(value = "/admin/update-api-verify-state")
    public RestResult updateApiVerifyState(@RequestBody Map map){
        String api_id= (String) map.get("api_id");
        Integer verify= (Integer) map.get("verify");
         Api api=new Api();
         api.setApi_id(api_id);
         api.setApi_verify_state(verify);
        try{
            apiService.updateApi(api);
            return new RestResult(1,"修改成功",null);
        }catch (Exception e){
            return new RestResult(0,"修改失败",e.toString());
        }
    }
    @RequestMapping(value = "/admin/update-api-adapt-state")
    public RestResult updateApiAdaptState(@RequestBody Map map) {
        String api_id = (String) map.get("api_id");
        Integer adapt = (Integer) map.get("adapt");
        Api api = new Api();
        api.setApi_id(api_id);
        api.setApi_adapt_state(adapt);
        try {
            apiService.updateApi(api);
            return new RestResult(1, "修改成功", null);
        } catch (Exception e) {
            return new RestResult(0, "修改失败", e.toString());
        }
    }
    /*想查询未审核的Api或者未适配的Api就传入对应的api模板
    * */
    @RequestMapping(value = "/admin/getapilist")
    public Map<String, Object> getApiListByTimeDesc(@RequestBody Map map){
        Api api=ClassUtil.mapToClass((Map) map.get("api"),Api.class);
        return apiService.getApiPageList((Integer) map.get("pageNo"),(Integer) map.get("pageSize"),api);
        //return null;
    }
    @RequestMapping(value = "/admin/modify-user-state")
    public RestResult modifyUserState(@RequestBody Map map){
        Integer role= (Integer) map.get("role");//role为0表示consumer，为1表示服务提供商

        return  null;
    }
}
