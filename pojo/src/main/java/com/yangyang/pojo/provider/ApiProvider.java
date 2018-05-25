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
    public String getApiPageListByApiExample(Integer pageNo, Integer pageSize, Api api,String key) throws IllegalAccessException {
        StringBuffer sql = new StringBuffer();
        sql.append("select a.*,s.sp_name,c.api_category_name ,d.price,d.content from api a,service_provider s,api_category c ,api_price d \n");
        List<String[]> condition = SqlUtil.getNotNullField(api);
        sql.append("where ");
        if(condition.size() != 0){
            sql.append(getWhere(condition));
        }
        sql.append(" a.sp_id=s.sp_id and a.api_category_id=c.api_category_id and a.api_id=d.api_id and d.price_type=2 and ");
        if (key!=null&&key!=""){
            sql.append(" ( a.api_name like '%"+key+"%' or a.api_description like '%"+key+"%' ) and");
        }
        sql.append( " 1=1 "+ " order by a.api_create_time desc limit " + ((pageNo -1) * pageSize)  + "," + pageSize);
        System.out.println(sql);
        return sql.toString();
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
    public String getApiListByApiExample(Integer pageNo,Integer pageSize,Api api) throws IllegalAccessException {
        StringBuffer sql = new StringBuffer();
        sql.append("select * from api \n");
        List<String[]> condition = SqlUtil.getNotNullField(api);
        //找到对第三方结账时的价格
        sql.append("where ");
        if(condition.size() != 0){
            sql.append(getWhere(condition));
        }
        sql.append( " 1=1 "+" limit " + ((pageNo -1) * pageSize)  + "," + pageSize);
        return sql.toString();
    }

    public String countPageList(Api api,String key) throws IllegalAccessException {
        StringBuffer sql = new StringBuffer();
        sql.append("select count(1) from api\n");
        sql.append("where ");
        List<String[]> condition = SqlUtil.getNotNullField(api);
        if(condition.size() != 0){
            sql.append(getWhere(condition));
        }
        if (key!=null&&key!=""){
            sql.append(" ( api_name like '%"+key+"%' or api_description like '%"+key+"%' ) and");
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
        Api api = new Api();
        api.setApi_name("test");
        api.setApi_description("test");
        api.setApi_url("http://test.com");
        api.setApi_normal_return_demo("{}");
        api.setApi_error_return_demo("{}");
        api.setApi_max_in(100);
        api.setApi_method(0);
        api.setApi_bill_type(0);
        api.setApi_param_xml("<?xml version='1.0' encoding='UTF-8' ?> <standardparam> <headers><header key='app_id' type='String' ismust='true' desc=' '>19329829</header><header key='app_secret' type='String' ismust='true' desc=' '>ankalala</header><header key='time_stamp' type='Long' ismust= 'true' desc=' '>132973982</header> </headers> </standardparam>");
        api.setApi_return_pattern("application/json");
        System.out.println(new ApiProvider().insertApi(api));
        System.out.println(new ApiProvider().getApiPageListByApiExample(1,100,new Api(),null));
    }
}
