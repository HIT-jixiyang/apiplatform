package com.yangyang.manage.web;

import com.sun.javafx.collections.MappingChange;
import com.yangyang.manage.component.FastDFSClient;
import com.yangyang.pojo.entity.*;
import com.yangyang.pojo.mapper.ConsumerMapper;
import com.yangyang.pojo.service.*;
import com.yangyang.utils.XmlUtil;
import com.yangyang.utils.utils.ApiCategoryID;
import com.yangyang.utils.utils.ClassUtil;
import com.yangyang.utils.utils.UUID;
import com.yangyang.utils.utils.UploadFileUtil;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.print.DocFlavor;
import javax.servlet.http.HttpServletRequest;
import java.io.StringReader;
import java.rmi.MarshalledObject;
import java.util.*;

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
    @Autowired
    ConsumerService consumerService;
    @Autowired
    SpService spService;
    @Autowired
    ConsumerMapper consumerMapper;
    @Autowired
    LeafService leafService;
    @Autowired
    LeafMapService leafMapService;
    private static final org.slf4j.Logger LOGGER = LoggerFactory.getLogger(AdminController.class);

    @PostMapping(value = "/admin/test-param-xml")
    public RestResult isParamXml(@RequestBody Map map) {
        String param_xml = (String) map.get("param_xml");
        return XmlUtil.getHeadersAndQuerysFromXml(param_xml);
    }

    @PostMapping(value = "/admin/add-apicategory")
    public RestResult addApiCategory(@RequestBody Map<String, Object> map) {
        Map<String, Object> api_category = (Map<String, Object>) map.get("api_category");

        ApiCategory apiCategory = ClassUtil.mapToClass(api_category, ApiCategory.class);

        apiCategory.setApi_category_id(ApiCategoryID.getID());
        System.out.println(apiCategory.toString());
        String param_xml = (String) map.get("param_xml");
        param_xml = param_xml.replace("\"", "'");
        String normal_response = apiCategory.getApi_category_normal_response();
        String error_response = apiCategory.getApi_category_error_response();
        apiCategory.setApi_category_normal_response(normal_response.replace("\"", "'"));
        apiCategory.setApi_category_error_response(error_response.replace("\"", "'"));
        apiCategory.setApi_category_param_xml(param_xml);
        String body = (String) map.get("body_param");
        StandardInboundParam param = new StandardInboundParam();
        param.setStandard_inbound_param_id(UUID.getUUID());
        param.setApi_category_id(apiCategory.getApi_category_id());
        if (body != null)
            param.setStandard_inbound_param_ismust(1);
        else
            param.setStandard_inbound_param_ismust(0);
        param.setStandard_inbound_param_value_demo(body);
        param.setStandard_inbound_param_type("String");
        param.setStandard_inbound_param_desc("");
        param.setStandard_inbound_param_position(2);
        param.setStandard_inbound_param_key("body");
        try {
            RestResult restResult = XmlUtil.getHeadersAndQuerysFromXml(param_xml);
            List<StandardInboundParam> list = (List<StandardInboundParam>) restResult.getData();
            List<StandardInboundParam> standardInboundParamList = new ArrayList<>();
            if (list.size() > 0) {
                for (StandardInboundParam param1 : list) {
                    param1.setApi_category_id(apiCategory.getApi_category_id());
                    param1.setStandard_inbound_param_id(UUID.getUUID());
                }
            }
            return apiCategoryService.addApiCategory(apiCategory, list);
        } catch (Exception e) {
            LOGGER.info(e.toString());
            return new RestResult(0, "error", e.toString());
        }
    }

    @PostMapping(value = "/admin/modify-apicategory")
    public RestResult modifyApiCategory(@RequestBody Map<String, Object> map) {
        return null;
    }

    //获取一页api类
    @PostMapping(value = "/admin/get-apicategorylist")
    public RestResult getApicategoryList(@RequestBody Map<String, Object> map) {
        return null;
    }

    @RequestMapping(value = "/admin/uploadjar", method = RequestMethod.POST)
    public @ResponseBody
    RestResult uploadImg(@RequestParam("file") MultipartFile file, @RequestParam("api_id") String api_id,
                         HttpServletRequest request) {
        String fileName = file.getOriginalFilename();
        System.out.println(api_id);
        Map<String, Object> map = new HashMap<>();
        /*System.out.println("fileName-->" + fileName);
        System.out.println("getContentType-->" + contentType);*/
        Api api = new Api();
        api.setApi_id(api_id);
        if (!fileName.endsWith("jar")) {
            map.put("info", "上传文件必须是jar文件");
            return new RestResult(ResultStatusCode.SYSTEM_ERROR.getStatuscode(), ResultStatusCode.SYSTEM_ERROR.getMessage(), map);
        }
        try {
            RestResult restResult = fastDFSClient.uploadFile(file);
            api.setApi_jar_path((String) restResult.getData());
            apiService.updateApi(api);
            return restResult;
        } catch (Exception e) {
            map.put("info", e.toString());
            return new RestResult(ResultStatusCode.SYSTEM_ERROR.getStatuscode(), "上传出错", map);
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
    public RestResult updateApiVerifyState(@RequestBody Map map) {
        String api_id = (String) map.get("api_id");
        Integer verify = (Integer) map.get("verify");
        Api api = new Api();
        api.setApi_id(api_id);
        api.setApi_verify_state(verify);
        try {
            apiService.updateApi(api);
            return new RestResult(1, "修改成功", null);
        } catch (Exception e) {
            return new RestResult(0, "修改失败", e.toString());
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
    public RestResult getApiListByTimeDesc(@RequestBody Map map) {

        Integer api_verify_state = (Integer) map.get("api_verify_state");
        Integer api_adapt_state = (Integer) map.get("api_adapt_state");
        String key = (String) map.get("key");
        Api api = new Api();
        if (api_verify_state != null) {
            api.setApi_verify_state(api_verify_state);
        }
        if (api_adapt_state != null) {
            api.setApi_adapt_state(api_adapt_state);
        }
        try {
            return new RestResult(1,"OK",apiService.getApiPageList((Integer) map.get("pageNo"), (Integer) map.get("pageSize"), api, key)) ;

        }catch (Exception e){
return new RestResult(0,"查询出错", e.toString()) ;
        }
        //Api api=ClassUtil.mapToClass((Map) map.get("api"),Api.class);
        //return null;
    }
    @RequestMapping(value = "/admin/getapidetail")
    public RestResult getApiDetailByApiID(@RequestBody Map map) {

        String api_id = (String) map.get("api_id");
        Api api = new Api();
        if(api_id!=null){
            api.setApi_id(api_id);
        }
        try{
            Map<String,Object> result=new HashMap<>();
            //Api api=ClassUtil.mapToClass((Map) map.get("api"),Api.class);

            result.put("api",apiService.getApidetailByApiID(api_id));
            result.put("headers","[]");
            result.put("querys","[]");
            result.put("body","{}");
            //return null;
            return new RestResult(1,"OK",result);

        }catch (Exception e){
            return new RestResult(0,"查询失败",e.toString());
        }
        }
    @RequestMapping(value = "/admin/user-auth")
    public RestResult modifyUserState(@RequestBody Map map) {
        Integer role = (Integer) map.get("role");//role为0表示consumer，为1表示服务提供商
        try {
            if (role == 0) {
                String consumer_id = (String) map.get("consumer_id");
                if (consumer_id == null) {
                    return new RestResult(0, "没有找到消费者编码，请检查", null);
                }
                Consumer consumer = new Consumer();
                consumer.setConsumer_id(consumer_id);
                consumer.setConsumer_state(1);
                consumerService.updateConsumer(consumer);
                return new RestResult(1, "修改成功", null);
            }
            if (role == 1) {
                String sp_id = (String) map.get("sp_id");
                if (sp_id == null) {
                    return new RestResult(0, "没有找到提供商编码，请检查", null);
                }
                Sp sp = new Sp();
                sp.setSp_id(sp_id);
                sp.setSp_state(1);
                spService.updateSp(sp);
                return new RestResult(1, "修改成功", null);
            }
            return new RestResult(0, "角色编码不对，请检查", null);
        } catch (Exception e) {
            return new RestResult(0, "修改失败", e.toString());
        }
        //return  null;
    }

    @PostMapping(value = "/admin/getuserlist")
    public RestResult getUserList(@RequestBody Map map) {
        Integer role = (Integer) map.get("role");//role为0表示consumer，为1表示服务提供商
        Integer pageNo = (Integer) map.get("pageNo");
        Integer pageSize = (Integer) map.get("pageSize");
        String key = (String) map.get("key");

        try {
            if (role == 0) {
                Consumer consumer = new Consumer();
                if (map.get("consumer") != null) {
                    consumer = ClassUtil.mapToClass((Map) map.get("consumer"), Consumer.class);
                }

                Map<String, Object> consumerMap = consumerService.getConsumerList(pageNo, pageSize, consumer, key);
                return new RestResult(1, "查询成功", consumerMap);
            }
            if (role == 1) {
                Sp sp = new Sp();
                if (map.get("sp") != null) {
                    sp = ClassUtil.mapToClass((Map) map.get("sp"), Sp.class);
                }
                Map<String, Object> spMap = spService.getSpList(pageNo, pageSize, sp, key);
                return new RestResult(1, "查询成功", spMap);
            }
            return new RestResult(0, "查询码错误", null);

        } catch (Exception e) {
            LOGGER.error(e.toString());
            return new RestResult(0, "查询失败", e.toString());
        }
    }

    @PostMapping(value = "/admin/get-sp-detail")
    public RestResult getSpDetail(@RequestBody Map map) {
        String sp_id = (String) map.get("sp_id");
        try {
            Map<String, Object> result = new HashMap<>();
            Sp sp = new Sp();
            sp.setSp_id(sp_id);
            Sp s = spService.getSpBySpID(sp_id);
            Api api = new Api();
            api.setSp_id(sp_id);
            Map<String, Object> apiMap = apiService.getApiList((Integer) map.get("pageNo"), (Integer) map.get("pageSize"), api);
            result.put("sp", s);
            result.put("apilist", apiMap);
            return new RestResult(1, "查询成功", result);

        } catch (Exception e) {
            return new RestResult(0, "查询失败", e.toString());
        }
    }

    @PostMapping(value = "/admin/get-consumer-detail")
    public RestResult getConsumerDetail(@RequestBody Map map) {
        String consumer_id = (String) map.get("consumer_id");
        try {
            Map<String, Object> result = new HashMap<>();

            if (consumer_id != null) {
                Consumer consumer = consumerMapper.getConsumerById(consumer_id);
                return new RestResult(1, "查询成功", consumer);
            }
            return new RestResult(0, "消费者编码不合法", null);
        } catch (Exception e) {
            return new RestResult(0, "查询失败", e.toString());
        }
    }

    @PostMapping(value = "/admin/addMapforApi")
    public RestResult adaptApiResponse(@RequestBody Map<String, Object> map) {
        String api_id = (String) map.get("api_id");
        String api_category_id = (String) map.get("api_category_id");
        Leaf leaf = new Leaf();
        leaf.setApi_id(api_id);
        List<Leaf> originLeafList = leafService.getLeafListByLeafExample(leaf);
        Leaf leaf1 = new Leaf();
        leaf1.setApi_category_id(api_category_id);
        List<Leaf> standardLeafList = leafService.getLeafListByLeafExample(leaf1);
        Map<Integer, Integer> leafmaps = (Map<Integer, Integer>) map.get("leafmaps");
        Set<Integer> set = leafmaps.keySet();
        List<LeafMap> leafMapList = new ArrayList<>();
        try {
            for (Integer key : set) {
                LeafMap leafMap = new LeafMap();
                leafMap.setApi_id(api_id);
                leafMap.setOrigin_leaf_id(key);
                leafMap.setStandard_leaf_id(leafmaps.get(key));
                leafMap.setOrigin_leaf_type(originLeafList.get(key).getLeaf_type());
                leafMap.setOrigin_leaf_format(originLeafList.get(key).getLeaf_format());
                leafMap.setStandard_leaf_format(standardLeafList.get(key).getLeaf_format());
                leafMapList.add(leafMap);
            }
            leafMapService.addMapList(leafMapList);
            return new RestResult(1, "适配成功", null);
        } catch (Exception e) {
            LOGGER.error(e.toString());
            return new RestResult(0, "适配失败", e.toString());
        }
    }

    @PostMapping(value = "get-leaf-infos")
    public RestResult getLeafInfos(@RequestBody Map map) {
        String api_id = (String) map.get("api_id");
        String api_category_id = (String) map.get("api_category_id");
        if(api_id!=null&&api_category_id!=null){
            Leaf apiLeafExample = new Leaf();
            apiLeafExample.setApi_id(api_id);
            Leaf api_categoryLeafExample = new Leaf();
            api_categoryLeafExample.setApi_category_id(api_category_id);
            List<Leaf> apileafList = leafService.getLeafListByLeafExample(apiLeafExample);
            List<Leaf> apicategotyLeafList = leafService.getLeafListByLeafExample(api_categoryLeafExample);
            Map<String, Object> resultMap = new HashMap<>();
            resultMap.put("apileaflist", apileafList);
            resultMap.put("apicategoryleaflist", apicategotyLeafList);
            return new RestResult(1, "OK", resultMap);
        }else {
            return new RestResult(0,"查询失败,请求信息没有发现api与apicategory的信息",null);
        }

    }
}
