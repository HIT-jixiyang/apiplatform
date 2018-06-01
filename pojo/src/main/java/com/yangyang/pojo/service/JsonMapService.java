package com.yangyang.pojo.service;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;

import com.yangyang.pojo.entity.Leaf;
import lombok.Getter;
import lombok.Setter;
import org.springframework.stereotype.Component;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import java.util.*;

//处理json的映射
@Getter
@Setter
@Component
public class JsonMapService {
    private String OriginJsonStr;
    private String StandardJsonStr;
    private int ID = 0;//ID用于编码
    private String api_id;
    private String api_category_id;
    private Object myobject;//用于返回处理后的报文
    private MultiValueMap<Integer, Leaf> LeafInfos = new LinkedMultiValueMap<>();
    private Map<Integer, Integer> leafmap;//叶子结点映射关系
//解析标准json报文
    @SuppressWarnings("rawtypes")
    public void analysisStandardJson(Object objJson, String parent_type, String path) {
        //如果obj为json数组

        if (objJson instanceof JSONArray) {
            JSONArray objArray = (JSONArray) objJson;
            for (int i = 0; i < objArray.size(); i++) {
                int id = ID;
                analysisStandardJson(objArray.get(i), "Array", path);
                if (i != objArray.size() - 1) {
                    ID = id;
                }
            }
        }
        //如果为json对象
        else if (objJson instanceof JSONObject) {
            JSONObject jsonObject = (JSONObject) objJson;
            Iterator it = jsonObject.keySet().iterator();
            while (it.hasNext()) {
                String key = it.next().toString();
                Object object = jsonObject.get(key);
                //如果得到的是数组
                if (object instanceof JSONArray) {
                    JSONArray objArray = (JSONArray) object;
                    analysisStandardJson(objArray, "Array", path + "." + key);
                }
                //如果key中是一个json对象
                else if (object instanceof JSONObject) {
                    analysisStandardJson((JSONObject) object, "Object", path + "." + key);
                }
                //如果key中是其他，代表是叶子了
                else {
                    ID++;
                    List<Leaf> leafList = new ArrayList<>();
                    if (LeafInfos.get(ID) == null) {
                        Leaf leaf = new Leaf();
                        leaf.setLeaf_id(ID);
                        leaf.setApi_category_id(api_category_id);
                        leaf.setLeaf_key(key);
                        leaf.setParent_type(parent_type);
                        leaf.setLeaf_value(object);
                        leaf.setLeaf_format("");
                        leaf.setLeaf_type(String.valueOf(object.getClass()));
                        leaf.setLeaf_path(path);
                        leafList.add(leaf);
        //以上是构造叶子信息哈希链表
                        this.LeafInfos.put(ID, leafList);
                    } else {
                        leafList = LeafInfos.get(ID);
                        Leaf leaf = new Leaf();
                        leaf.setLeaf_id(ID);
                        leaf.setApi_category_id(api_category_id);
                        leaf.setLeaf_key(key);
                        leaf.setParent_type(parent_type);
                        leaf.setLeaf_value(object);
                        leaf.setLeaf_format("");
                        leaf.setLeaf_type(String.valueOf(object.getClass()));
                        leaf.setLeaf_path(path);
                        leafList.add(leaf);
                        this.LeafInfos.put(ID, leafList);
                    }
                    System.out.println(ID + "------" + "[" + key + "]:" + object.toString() + " " + parent_type + "---" + path);
                }
            }
        }
    }
//解析原始报文
    @SuppressWarnings("rawtypes")
    public void analysisOriginJson(Object objJson, String parent_type, String path) {
        //如果obj为json数组

        if (objJson instanceof JSONArray) {
            JSONArray objArray = (JSONArray) objJson;
            for (int i = 0; i < objArray.size(); i++) {

                int id = ID;
                analysisOriginJson(objArray.get(i), "Array", path);
                if (i != objArray.size() - 1) {
                    ID = id;
                }
            }
        }
        //如果为json对象
        else if (objJson instanceof JSONObject) {
            JSONObject jsonObject = (JSONObject) objJson;
            Iterator it = jsonObject.keySet().iterator();
            while (it.hasNext()) {
                String key = it.next().toString();
                Object object = jsonObject.get(key);
                //如果得到的是数组
                if (object instanceof JSONArray) {
                    JSONArray objArray = (JSONArray) object;
                    analysisOriginJson(objArray, "Array", path + "." + key);
                }
                //如果key中是一个json对象
                else if (object instanceof JSONObject) {
                    analysisOriginJson((JSONObject) object, "Object", path + "." + key);
                }
                //如果key中是其他
                else {
                    ID++;
                    List<Leaf> leafList = new ArrayList<>();
                    if (LeafInfos.get(ID) == null) {
                        //构造叶子信息哈希链表
                        Leaf leaf = new Leaf();
                        leaf.setLeaf_id(ID);
                        leaf.setApi_id(api_id);
                        leaf.setLeaf_key(key);
                        leaf.setParent_type(parent_type);
                        leaf.setLeaf_value(object);
                        leaf.setLeaf_format("");
                        leaf.setLeaf_type(String.valueOf(object.getClass()));
                        leaf.setLeaf_path(path);
                        leafList.add(leaf);
                        this.LeafInfos.put(ID, leafList);
                    } else {
                        leafList = LeafInfos.get(ID);
                        Leaf leaf = new Leaf();
                        leaf.setLeaf_id(ID);
                        leaf.setApi_id(api_id);
                        leaf.setLeaf_key(key);
                        leaf.setParent_type(parent_type);
                        leaf.setLeaf_value(object);
                        leaf.setLeaf_format("");
                        leaf.setLeaf_type(String.valueOf(object.getClass()));
                        leaf.setLeaf_path(path);
                        leafList.add(leaf);
                        this.LeafInfos.put(ID, leafList);
                    }
                    System.out.println(ID + "------" + "[" + key + "]:" + object.toString() + " " + parent_type + "---" + path);
                }
            }
        }
    }

    //获得原始叶子信息
    public MultiValueMap<Integer, Leaf> getOriginLeafInfos(String api_id, String originJsonstr) {
        try {
            this.api_id = api_id;
            ID = 0;
            Object o = JSONArray.parseArray(originJsonstr);
            analysisOriginJson(o, "Array", "root");
        } catch (Exception e) {
            try {
                JSONObject jsonObject = JSONObject.parseObject(originJsonstr);
                analysisOriginJson(jsonObject, "Object", "root");
            } catch (Exception e1) {
                System.out.println("字符串逗我呢");
            }
        }
        //hw.analysisOriginJson(jsonObject,"Object");
        return this.LeafInfos;
    }

    //获得标准json叶子信息
    public MultiValueMap<Integer, Leaf> getStandardLeafInfos(String api_category_id, String standardJson) {
        try {
            this.api_category_id = api_category_id;
            ID = 0;
            Object o = JSONArray.parseArray(standardJson);
            analysisStandardJson(o, "Array", "root");
        } catch (Exception e) {
            try {
                JSONObject jsonObject = JSONObject.parseObject(standardJson);
                analysisStandardJson(jsonObject, "Object", "root");
            } catch (Exception e1) {
                System.out.println("字符串逗我呢");
            }
        }
        //hw.analysisOriginJson(jsonObject,"Object");
        return this.LeafInfos;
    }
//由标准的json报文模板和
    private void buildStandardJson(Object objJson, String parent_type, int index) {
        //如果obj为json数组

        if (objJson instanceof JSONArray) {
            JSONArray objArray = (JSONArray) objJson;
            for (int i = 0; i < objArray.size(); i++) {

                int id = ID;
                buildStandardJson(objArray.get(i), "Array", i);
                if (i != objArray.size() - 1) {
                    ID = id;
                }
            }
        }
        //如果为json对象
        else if (objJson instanceof JSONObject) {
            JSONObject jsonObject = (JSONObject) objJson;
            Iterator it = jsonObject.keySet().iterator();
            while (it.hasNext()) {
                String key = it.next().toString();
                Object object = jsonObject.get(key);
                //如果得到的是数组
                if (object instanceof JSONArray) {
                    JSONArray objArray = (JSONArray) object;
                    buildStandardJson(objArray, "Array", 0);
                }
                //如果key中是一个json对象
                else if (object instanceof JSONObject) {
                    buildStandardJson((JSONObject) object, "Object", 0);
                }
                //如果key中是其他
                else {
                    ID++;
                    Integer preID = leafmap.get(ID);
                    if (preID != null) {
                        //修改标准报文的叶子，改成和原始报文对应的叶子信息
                        System.out.println("当前key"+key+"  当前原始叶子信息  ----"+LeafInfos.get(leafmap.get(ID)).get(index).getLeaf_value());
                        jsonObject.put(key, LeafInfos.get(leafmap.get(ID)).get(index).getLeaf_value());
                        object = LeafInfos.get(leafmap.get(ID)).get(index);
                        System.out.println(ID + "------" + "[" + key + "]:" + LeafInfos.get(leafmap.get(ID)).get(index).getLeaf_value() + " TYPE :" + LeafInfos.get(leafmap.get(ID)).get(index).getLeaf_value().getClass());
                    } else {
                        System.out.println(ID + "------" + "[" + key + "]:" + object);
                    }
                }
            }
        }
    }

    //给定标准的json报文，原始的报文，两者叶子映射关系，即可得到处理后的报文
    public String getHandledString(String standardJson, String originJsonStr, Map<Integer, Integer> leafmap) {
     //  this.setLeafInfos(null);
        this.LeafInfos = getOriginLeafInfos("", originJsonStr);
        this.leafmap = leafmap;
        ID = 0;
        try {
            myobject = JSONArray.parseArray(standardJson);
            buildStandardJson(myobject, "Array", 0);
        } catch (Exception e) {
            try {
                myobject = JSONObject.parseObject(standardJson);
                buildStandardJson(myobject, "Object", 0);
            } catch (Exception e1) {
                System.out.println("字符串逗我呢");
                System.out.println(e1.toString());
            }
        }
        return JSONObject.toJSONString(myobject);
    }

    public static void main(String[] args) {
        String standardJson = "{" +
                "  'data': {" +
                "    'yesterday': {" +
                "      'date': '22日星期二'," +
                "      'high': '高温 18℃'," +
                "      'fengxiang': ''," +
                "      'low': '低温 13℃'," +
                "      'fl': '<![CDATA[4-5级]]>'," +
                "      'type': '中雨'" +
                "    }," +
                "    'city': '威海'," +
                "    'forecast': [" +
                "      {" +
                "        'date': '23日星期三'," +
                "        'high': '高温 24℃'," +
                "        'fengli': '<![CDATA[4-5级]]>'," +
                "        'low': '低温 15℃'," +
                "        'fengxiang': ''," +
                "        'type': '晴'" +
                "      }," +
                "      {" +
                "        'date': '24日星期四'," +
                "        'high': '高温 29℃'," +
                "        'fengli': '<![CDATA[6-7级]]>'," +
                "        'low': '低温 18℃'," +
                "        'fengxiang': ''," +
                "        'type': '晴'" +
                "      }," +
                "      {" +
                "        'date': '25日星期五'," +
                "        'high': '高温 25℃'," +
                "        'fengli': '<![CDATA[4-5级]]>'," +
                "        'low': '低温 16℃'," +
                "        'fengxiang': ''," +
                "        'type': '晴'" +
                "      }," +
                "      {" +
                "        'date': '26日星期六'," +
                "        'high': '高温 25℃'," +
                "        'fengli': '<![CDATA[4-5级]]>'," +
                "        'low': '低温 18℃'," +
                "        'fengxiang': ''," +
                "        'type': '多云'" +
                "      }," +
                "      {" +
                "        'date': '27日星期天'," +
                "        'high': '高温 24℃'," +
                "        'fengli': '<![CDATA[4-5级]]>'," +
                "        'low': '低温 15℃'," +
                "        'fengxiang': ''," +
                "        'type': '小雨'" +
                "      }" +
                "    ]," +
                "    'ganmao': '虽然温度适宜但风力较大，仍较易发生感冒，体质较弱的朋友请注意适当防护。'," +
                "    'wendu': '18'" +
                "  }," +
                "  'status': 1000," +
                "  'desc': 'OK'" +
                "}";
        String originJson1 = "{\"city\":\"威海\",\"weather\":{\"data\":{\"yesterday\":{\"date\":\"16日\",\"high\":\"高温 28℃\",\"fx\":\"南风\",\"low\":\"低温 19℃\",\"fl\":\"<![CDATA[5-6级]]>\",\"type\":\"小雨\"},\"forecast\":[{\"date\":\"17日\",\"high\":\"高温 25℃\",\"fx\":\"南风\",\"low\":\"低温 15℃\",\"fl\":\"<![CDATA[4-5级]]>\",\"type\":\"小雨\"},{\"date\":\"18日\",\"high\":\"高温 22℃\",\"fx\":\"北风\",\"low\":\"低温 14℃\",\"fl\":\"<![CDATA[4-5级]]>\",\"type\":\"晴\"},{\"date\":\"19日\",\"high\":\"高温 20℃\",\"fx\":\"东风\",\"low\":\"低温 14℃\",\"fl\":\"<![CDATA[4-5级]]>\",\"type\":\"晴\"},{\"date\":\"20日\",\"high\":\"高温 22℃\",\"fx\":\"东南风\",\"low\":\"低温 14℃\",\"fl\":\"<![CDATA[4-5级]]>\",\"type\":\"多云\"},{\"date\":\"21日\",\"high\":\"高温 22℃\",\"fx\":\"东南风\",\"low\":\"低温 14℃\",\"fl\":\"<![CDATA[4-5级]]>\",\"type\":\"阴\"}],\"ganmao\":\"虽然温度适宜但风力较大，仍较易发生感冒，体质较弱的朋友请注意适当防护。\",\"wendu\":\"19\"}},\"status\":\"200\"}\n";
        Map<Integer, Integer> map = new HashMap<>();
        for (int i = 1; i <= 17; i++) {
            map.put(i, i);
        }
        map.put(4, 3);//标准json的4号叶子将对应到原始json的3号叶子
        map.put(3, 4);//标准json的3号叶子将对应到原始json的4号叶子
        String json = new JsonMapService().getHandledString(standardJson, originJson1, map);
        System.out.println(json);
        MultiValueMap<Integer, Leaf> map1 = new JsonMapService().getOriginLeafInfos("", originJson1);
        MultiValueMap<Integer, Leaf> map2 = new JsonMapService().getStandardLeafInfos("", standardJson);
        //  System.out.println(new JsonMapService().getStandardLeafInfos("",standardJson));
    }
}
