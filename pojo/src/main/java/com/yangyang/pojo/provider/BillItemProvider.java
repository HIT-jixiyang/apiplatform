package com.yangyang.pojo.provider;



import com.yangyang.pojo.entity.BillItem;
import com.yangyang.utils.utils.SqlUtil;

import java.util.List;

/**
 * @program: apiplatform
 * @description: 动态生成order查询语句
 * @author: JiXiYang
 * @create: 2018-05-01 16:27
 **/
public class BillItemProvider {

    public String getBillItemPageListByBillItemExample(Integer pageNo, Integer pageSize, BillItem billItem) throws IllegalAccessException {
        StringBuffer sql = new StringBuffer();
        sql.append("select * from bill_item\n");
        List<String[]> condition = SqlUtil.getNotNullField(billItem);
        sql.append("where ");
        if(condition.size() != 0){
            sql.append(getWhere(condition));
        }
        sql.append( " 1=1");
        return sql.toString() + " limit " + ((pageNo -1) * pageSize)  + "," + pageSize;
    }
    public String getBillItemPageListByBillItemExampleAndTime(Integer pageNo, Integer pageSize, BillItem billItem,String api_name,String beginTime,String endTime) throws IllegalAccessException {
        StringBuffer sql = new StringBuffer();
        sql.append("select a.*,b.api_name from bill_item a,api b\n");
        List<String[]> condition = SqlUtil.getNotNullField(billItem);
        sql.append("where ");
        if(beginTime!=null&&endTime!=null)
        sql.append("create_time >= "+"\'"+beginTime+"\'" +" and create_time<= "+"\'"+endTime+"\'"+"\n and ");
        if(condition.size() != 0){
            sql.append(getWhere(condition));
        }
        //select a.*,b.api_name from bill_item a,api b
        //where `app_id`=4 AND a.api_id=b.api_id and b.api_name LIKE '%加法%' and 1=1 LIMIT 0,100;
        sql.append(" a.api_id=b.api_id and b.api_name LIKE "+"\'"
                +"%"+api_name+"%"+"\'"+" and ");
        sql.append( " 1=1");
        System.out.println(sql.toString());
        return sql.toString() + " limit " + ((pageNo -1) * pageSize)  + "," + pageSize;
    }
    public String getBillItemCountByAppIDAndApiName(BillItem billItem, String api_name,String beginTime, String endTime) throws IllegalAccessException {
        StringBuffer sql = new StringBuffer();
        sql.append("select count(*) from bill_item a,api b\n");
        List<String[]> condition = SqlUtil.getNotNullField(billItem);
        sql.append("where ");
        if(beginTime!=null&&endTime!=null)
            sql.append("create_time >= "+"\'"+beginTime+"\'" +" and create_time<= "+"\'"+endTime+"\'"+"\n and ");
        if(condition.size() != 0){
            sql.append(getWhere(condition));
        }
        sql.append(" a.api_id=b.api_id and b.api_name LIKE "+"\'"
                +"%"+api_name+"%"+"\'"+" and ");
        sql.append( " 1=1");
        System.out.println(sql.toString());
        return sql.toString();
    }



    public String countPageList(BillItem billItem) throws IllegalAccessException {
        StringBuffer sql = new StringBuffer();
        sql.append("select count(1) from bill_item\n");
        sql.append("where ");
        List<String[]> condition = SqlUtil.getNotNullField(billItem);
        if(condition.size() != 0){
            sql.append(getWhere(condition));
        }
        sql.append(" 1=1");
        return sql.toString();
    }
    public String updateBillItemByBillExample(BillItem billItem) throws IllegalAccessException {
        List<String[]> condition = SqlUtil.getNotNullField(billItem);
        StringBuffer sql = new StringBuffer();
        sql.append("update bill_item set \n");
        sql.append(getSet(condition));
        sql.append(" where bill_item_id ="+"\""+billItem.getBill_item_id()+"\"");
        return sql.toString();
    }
    private String getSet(List<String[]> condition){
        StringBuffer set = new StringBuffer();
        int len = condition.size();
        for(int i = 0; i < len-1; i++){
            set.append(condition.get(i)[0] + "=" + condition.get(i)[1] + " ,");
        }
        set.append(condition.get(len-1)[0] + "=" + condition.get(len-1)[1]);
        return set.toString();
    }
    private String getWhere(List<String[]> condition){
        StringBuffer where = new StringBuffer();
        for(int i = 0, len = condition.size(); i < len; i++){
           // if(!condition.get(i)[0].equals("Api_name"))
            where.append(condition.get(i)[0] + "=" + condition.get(i)[1] + " and ");
 // else {
      //          where.append(condition.get(i)[0] +" like %"+condition.get(i)[1]+"% " + " and ");
          //  }
        }
        return where.toString();
    }
    public String getAverageResponseTimeByApiID(int offset,String api_id){
        StringBuffer buffer=new StringBuffer();
        buffer.append("select AVG(request_time) from bill_item ");
        buffer.append("where api_id= ");
        buffer.append("\""+api_id+"\"");
        buffer.append("and response_code='200' ");
        buffer.append("ORDER BY create_time DESC LIMIT ");
        buffer.append("0,"+offset);
        return buffer.toString();
}
//SELECT count(*) FROM (SELECT * from bill_item a WHERE api_id="ef7deaca96d94cfeb21c1985c44525db" ORDER BY create_time DESC LIMIT 0,20) b WHERE b.response_code="200";
public String getResponseTimesByApiIDAndStatusCode(int offset,String api_id,String response_code){
        StringBuffer buffer=new StringBuffer();
        buffer.append("select count(*) from ");
        buffer.append("( select * from bill_item a ");
        buffer.append("where api_id= ");
        buffer.append("\""+api_id+"\""+" ORDER BY create_time DESC ");
        buffer.append("limit 0,"+offset+") b");
        buffer.append(" WHERE b.response_code= ");
        buffer.append("\""+response_code+"\"");
        return buffer.toString();
}

    public String getAverageResponseTimeByApiCategory(int offset,String api_category_id){
        StringBuffer buffer=new StringBuffer();
        buffer.append("select AVG(request_time) from bill_item ");
        buffer.append("where api_id in ");
        buffer.append(" ( select api_id from api where api_category_id=");
        buffer.append(api_category_id+") ");
        buffer.append(" and response_code='200' ");
        buffer.append("ORDER BY create_time DESC LIMIT ");
        buffer.append("0,"+offset);
        return buffer.toString();
    }
    public static void main(String[] args) {
        //  System.out.println(new BillItemProvider().getNormalResponseTimesByApiID(20,"ef7deaca96d94cfeb21c1985c44525db","200"));
        System.out.println(new BillItemProvider().getAverageResponseTimeByApiCategory(100,"0"));
    }
}
