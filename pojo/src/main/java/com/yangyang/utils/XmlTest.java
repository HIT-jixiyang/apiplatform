package com.yangyang.utils;

import org.dom4j.Attribute;
import org.dom4j.Document;
import org.dom4j.DocumentHelper;
import org.dom4j.Element;

import java.util.Iterator;

public class XmlTest {
    public static void main(String[] args) {
        try {
            String xmlResult = "<standardparam>\n" +
                    "    <header>\n" +
                    "        <param key=\"app_id\" type=\"String\" ismust=\"true\" desc=\"\">19329829</param>\n" +
                    "        <param key=\"app_secret\" type=\"String\" ismust=\"true\">ankalala</param>\n" +
                    "        <param key=\"time_stamp\" type=\"Long\" ismust=\"true\">132973982</param>\n" +
                    "    </header>\n" +
                    "    <query>\n" +
                    "    </query>\n" +
                    "    <body>\n" +
                    "        <param key=\"city\" type=\"String\" format=\"\" position=\"header\" ismust=\"true\">北京</param>\n" +
                    "        <param key=\"date\" type=\"String\" format=\"YYMMDDHH\" position=\"body\" ismust=\"false\">2018-05-21 </param>\n" +
                    "        <param key=\"list\" type=\"List\">\n" +
                    "            <param key=\"listitem1\" type=\"String\">listitem1 </param>\n" +
                    "            <param key=\"listitem2\" type=\"String\"> listitem2</param>\n" +
                    "            <param key=\"listitem3\" type=\"String\">listitem3 </param>\n" +
                    "        </param>\n" +
                    "        <param key=\"list\" type=\"Object\">\n" +
                    "            <param key=\"listitem1\" type=\"String\">listitem </param>\n" +
                    "            <param key=\"listitem2\" type=\"String\"> listitem2</param>\n" +
                    "            <param key=\"listitem3\" type=\"String\">listitem3 </param>\n" +
                    "        </param>\n" +
                    "    </body>\n" +
                    "\n" +
                    "</standardparam>";
            // 将xml格式字符串转化为DOM对象
            Document document = DocumentHelper.parseText(xmlResult);
            // 获取根结点对象
            Element rootElement = document.getRootElement();
            // 循环根节点，获取其子节点
            for (Iterator iter = rootElement.elementIterator(); iter.hasNext();) {
                Element element = (Element) iter.next(); // 获取标签对象
                // 获取该标签对象的属性

                // 循环第一层节点，获取其子节点
                for (Iterator iterInner = element.elementIterator(); iterInner
                        .hasNext();) {
                    // 获取标签对象
                    Element elementOption = (Element) iterInner.next();
                    // 获取该标签对象的名称
                    String tagName = elementOption.getName();
                    Attribute keyattr = elementOption.attribute("key");
                    // 获取该标签对象的内容
                    String tagContent = elementOption.getTextTrim();
                    // 输出内容
                    System.out.print( "key:"+keyattr.getValue()+"   value:"  + tagContent +"  ");
                }
                System.out.println();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}