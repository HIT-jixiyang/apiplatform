package com.yangyang.utils;

import com.yangyang.pojo.entity.RestResult;
import com.yangyang.pojo.entity.StandardInboundParam;
import org.dom4j.*;

import java.util.*;

public class XmlUtil {

    public static RestResult isXml(String xmlStr) {
        Map<Integer, String> map = new HashMap<>();
        org.dom4j.Document document;
        try {
            document = DocumentHelper.parseText(xmlStr);

        } catch (DocumentException e) {
            e.printStackTrace();
            map.put(0, e.toString());
            return new RestResult(0, "校验失败", e.toString());
        }
        map.put(1, "校验成功");
        return new RestResult(1, "校验成功", document);
    }

    //获得头参数与query参数信息
    public static RestResult getHeadersAndQuerysFromXml(String xml) {
        List<StandardInboundParam> standardInboundParamList = new ArrayList<>();
        RestResult restResut = isXml(xml);
        if (restResut.getStatus() == 0) {
            return restResut;
        } else {
            Document document = (Document) restResut.getData();
            Element rootElement = document.getRootElement();
            // 循环根节点，获取其子节点
            for (Iterator iter = rootElement.elementIterator(); iter.hasNext(); ) {
                Element element = (Element) iter.next(); // 获取标签对象
                // 获取该标签对象的属性

                if (element.getName().equals("headers")) {
                    for (Iterator iterInner = element.elementIterator(); iterInner
                            .hasNext(); ) {
                        // 获取标签对象
                        Element elementOption = (Element) iterInner.next();
                        // 获取该标签对象的名称
                        String tagName = elementOption.getName();
                        Attribute keyattr = elementOption.attribute("key");
                        Attribute typeattr = elementOption.attribute("type");
                        Attribute descattr = elementOption.attribute("desc");
                        Attribute formatattr = elementOption.attribute("format");
                        Attribute ismustattr = elementOption.attribute("ismust");
                        // 获取该标签对象的内容
                        if(!typeattr.getValue().equals("String")&&!typeattr.getValue().equals("Integer")&&!typeattr.getValue().equals("Long")
                                &&!typeattr.getValue().equals("Double")&&!typeattr.getValue().equals("Date")){
                            return new RestResult(0,"参数类型不正确，请检查",null);
                        }
                        if (!ismustattr.getValue().equals("true")&&!ismustattr.getValue().equals("false")){
                            return new RestResult(0,"ismust字段必须为true或者false，请检查",null);
                        }
                        String value = elementOption.getTextTrim();
                        // 输出内容
                        StandardInboundParam standardInboundParam = new StandardInboundParam();

                        standardInboundParam.setStandard_inbound_param_type(typeattr.getValue());
                        standardInboundParam.setStandard_inbound_param_key(keyattr.getValue());
                        standardInboundParam.setStandard_inbound_param_position(0);
                        standardInboundParam.setStandard_inbound_param_desc(descattr.getValue()==null?"":descattr.getValue());
                        standardInboundParam.setStandard_inbound_param_ismust(ismustattr.getValue().equals("true")?1:0);
                        standardInboundParam.setStandard_inbound_param_value_demo(value);
                        standardInboundParamList.add(standardInboundParam);
                        //System.out.print("key:" + keyattr.getValue() + "   value:" + tagContent + "  ");
                    }
                }
                if (element.getName().equals("querys")) {
                    for (Iterator iterInner = element.elementIterator(); iterInner
                            .hasNext(); ) {
                        // 获取标签对象
                        Element elementOption = (Element) iterInner.next();
                        // 获取该标签对象的名称
                        String tagName = elementOption.getName();
                        Attribute keyattr = elementOption.attribute("key");
                        Attribute typeattr = elementOption.attribute("type");
                        Attribute descattr = elementOption.attribute("desc");
                        Attribute formatattr = elementOption.attribute("format");
                        Attribute ismustattr = elementOption.attribute("ismust");
                        // 获取该标签对象的内容
                        if(!typeattr.getValue().equals("String")&&!typeattr.getValue().equals("Integer")&&!typeattr.getValue().equals("Long")
                        &&!typeattr.getValue().equals("Double")&&!typeattr.getValue().equals("Date")){
                         return new RestResult(0,"参数类型不正确，请检查",null);
                        }
                        if (!ismustattr.getValue().equals("true")&&!ismustattr.getValue().equals("false")){
                            return new RestResult(0,"ismust字段必须为true或者false，请检查",null);
                        }
                        String value = elementOption.getTextTrim();
                        // 输出内容
                        StandardInboundParam standardInboundParam = new StandardInboundParam();

                        standardInboundParam.setStandard_inbound_param_type(typeattr.getValue());
                        standardInboundParam.setStandard_inbound_param_key(keyattr.getValue());
                        standardInboundParam.setStandard_inbound_param_position(1);
                        standardInboundParam.setStandard_inbound_param_desc(descattr.getValue()==null?"":descattr.getValue());
                        standardInboundParam.setStandard_inbound_param_ismust(ismustattr.getValue().equals("true")?1:0);
                        standardInboundParam.setStandard_inbound_param_value_demo(value);
                        standardInboundParamList.add(standardInboundParam);
                        //System.out.print("key:" + keyattr.getValue() + "   value:" + tagContent + "  ");
                    }

                }
                // 循环第一层节点，获取其子节点
            }

        }
    //    return null;
        return new RestResult(1,"转化成功",standardInboundParamList);
    }

    public static void main(String[] args) {
        String xml = "<standardparam>\n" +
                "    <headers>\n" +
                "        <param key='app_id' type='String' ismust='true' desc=' '>19329829</param>\n" +
                "        <param key='app_secret' type='String' ismust='true' desc=' '>ankalala</param>\n" +
                "        <param key='time_stamp' type='Long' ismust= 'true' desc=' '>132973982</param>\n" +
                "    </headers>\n" +
                "    <querys>\n" +
                "    </querys>\n" +
                "    <body>\n" +
                "        <param key='city' type='String' format=''  ismust='true'>北京</param>\n" +
                "        <param key='date' type='String' format='YYMMDDHH'  ismust='false'>2018-05-21 </param>\n" +
                "        <param key='list' type='List'>\n" +
                "            <param key='listitem1' type='String'>listitem1 </param>\n" +
                "            <param key='listitem2' type='String'> listitem2</param>\n" +
                "            <param key='listitem3' type='String'>listitem3 </param>\n" +
                "        </param>\n" +
                "        <param key='list' type='Object'>\n" +
                "            <param key='listitem1' type='String'>listitem </param>\n" +
                "            <param key='listitem2' type='String'> listitem2</param>\n" +
                "            <param key='listitem3' type='String'>listitem3 </param>\n" +
                "        </param>\n" +
                "    </body>\n" +
                "\n" +
                "</standardparam>";
       RestResult restResult= getHeadersAndQuerysFromXml(xml);
       List<StandardInboundParam> list= (List<StandardInboundParam>) restResult.getData();
    }
}
