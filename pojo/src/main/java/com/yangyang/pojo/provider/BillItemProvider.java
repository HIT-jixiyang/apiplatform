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
            where.append(condition.get(i)[0] + "=" + condition.get(i)[1] + " and");
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

    public static void main(String[] args) {
      //  System.out.println(new BillItemProvider().getNormalResponseTimesByApiID(20,"ef7deaca96d94cfeb21c1985c44525db","200"));
        BillItem billItem=new BillItem();
        billItem.setApi_id("ef7deaca96d94cfeb21c1985c44525db");
        try {
            System.out.println(new BillItemProvider().getBillItemPageListByBillItemExample(new Integer(1),new Integer(100),billItem));
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        }
    }
}
