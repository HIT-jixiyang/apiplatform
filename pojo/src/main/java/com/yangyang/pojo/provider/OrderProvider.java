package com.yangyang.pojo.provider;



import com.yangyang.pojo.entity.Order;
import com.yangyang.utils.utils.SqlUtil;

import java.util.List;

/**
 * @program: apiplatform
 * @description: 动态生成order查询语句
 * @author: JiXiYang
 * @create: 2018-05-01 16:27
 **/
public class OrderProvider {
    public String getOrderPageListByOrderExample(Integer pageNo, Integer pageSize, Order order) throws IllegalAccessException {
        StringBuffer sql = new StringBuffer();
        sql.append("select * from `order`\n");
        List<String[]> condition = SqlUtil.getNotNullField(order);
        sql.append("where ");
        if(condition.size() != 0){
            sql.append(getWhere(condition));
        }
        sql.append( " 1=1");
        return sql.toString() + " limit " + ((pageNo -1) * pageSize)  + "," + pageSize;
    }
/*public String modifyOrder(Order order) throws IllegalAccessException {
        StringBuffer sql=new StringBuffer();
        sql.append("update `order` \n");

        List<String[]> condition=SqlUtil.getNotNullField(order);

    }*/
    public String countPageList(Order order) throws IllegalAccessException {
        StringBuffer sql = new StringBuffer();
        sql.append("select count(1) from `order`\n");
        sql.append("where ");
        List<String[]> condition = SqlUtil.getNotNullField(order);
        if(condition.size() != 0){
            sql.append(getWhere(condition));
        }
        sql.append(" 1=1");
        return sql.toString();
    }

    private String getWhere(List<String[]> condition){
        StringBuffer where = new StringBuffer();
        for(int i = 0, len = condition.size(); i < len; i++){
            where.append(condition.get(i)[0] + "=" + condition.get(i)[1] + " and");
        }
        return where.toString();
    }
}
