package com.yangyang.pojo.provider;


import com.yangyang.pojo.entity.Api;
import com.yangyang.pojo.entity.BillItem;
import com.yangyang.utils.utils.SqlUtil;

import java.util.List;

/**
 * @Author: ny
 * @Date: Created in 9:25 2018/4/7 0007
 */
public class ApiProvider {
    public String getApiPageListByApiExample(Integer pageNo, Integer pageSize, Api api) throws IllegalAccessException {
        StringBuffer sql = new StringBuffer();
        sql.append("select a.*,s.sp_name,c.api_category_name from api a,service_provider s,api_category c \n");
        List<String[]> condition = SqlUtil.getNotNullField(api);
        sql.append("where ");
        if(condition.size() != 0){
            sql.append(getWhere(condition));
        }
        sql.append(" a.sp_id=s.sp_id and a.api_category_id=c.api_category_id and");
        sql.append( " 1=1");
        return sql.toString() + " limit " + ((pageNo -1) * pageSize)  + "," + pageSize;
    }


    public String getApiAndPriceListByApiExample(Api api) throws IllegalAccessException {
        StringBuffer sql = new StringBuffer();
        sql.append("select a.*,b.price,b.content from api a,api_price b \n");
        List<String[]> condition = SqlUtil.getNotNullField(api);
        //找到对第三方结账时的价格
        sql.append("where a.api_id=b.api_id and b.price_type=2 and ");
        if(condition.size() != 0){
            sql.append(getWhere(condition));
        }
        sql.append( " 1=1");
        return sql.toString();
    }
    public String getApiListByApiExample(Api api) throws IllegalAccessException {
        StringBuffer sql = new StringBuffer();
        sql.append("select * from api \n");
        List<String[]> condition = SqlUtil.getNotNullField(api);
        //找到对第三方结账时的价格
        sql.append("where ");
        if(condition.size() != 0){
            sql.append(getWhere(condition));
        }
        sql.append( " 1=1");
        return sql.toString();
    }

    public String countPageList(Api api) throws IllegalAccessException {
        StringBuffer sql = new StringBuffer();
        sql.append("select count(1) from api\n");
        sql.append("where ");
        List<String[]> condition = SqlUtil.getNotNullField(api);
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
    public String updateApiByApiExample(Api api) throws IllegalAccessException {

        List<String[]> condition = SqlUtil.getNotNullField(api);
        StringBuffer sql = new StringBuffer();
        sql.append("update api set \n");
        sql.append(getSet(condition));
        sql.append(" where api_id ="+"\""+api.getApi_id()+"\"");
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
public   String insertApi(Api api) throws IllegalAccessException {
    List<String[]> condition = SqlUtil.getNotNullField(api);
    StringBuffer sql = new StringBuffer();
    sql.append("insert into api(");
    sql.append(getApiColumn(condition));
    sql.append(") values (");
    sql.append(getApiValues(condition)+")");
    return sql.toString();
}
private  String getApiColumn(List<String[]> condition){
    StringBuffer columns = new StringBuffer();
    int len = condition.size();
    for(int i = 0; i < len-1; i++){
        columns.append(condition.get(i)[0] +  " ,");
    }
    columns.append(condition.get(len-1)[0] );
    return  columns.toString();
}
private String getApiValues(List<String[]> condition){
StringBuffer values=new StringBuffer();
    for(int i = 0; i < condition.size()-1; i++){
        values.append(condition.get(i)[1] +  " ,");
    }
    values.append(condition.get(condition.size()-1)[1]);
    return values.toString();
}
    public static void main(String[] args) throws IllegalAccessException {
        Api api=new Api();

        System.out.println(new ApiProvider().getApiPageListByApiExample(1,90,api));
    }
}
