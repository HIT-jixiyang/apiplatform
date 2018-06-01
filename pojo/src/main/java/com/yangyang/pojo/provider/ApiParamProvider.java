package com.yangyang.pojo.provider;


import com.yangyang.pojo.entity.ApiParam;
import com.yangyang.utils.utils.SqlUtil;

import java.util.List;

/**
 * @Author: ny
 * @Date: Created in 9:25 2018/4/7 0007
 */
public class ApiParamProvider {

    public String getApiParamListByApiParamExample(Integer pageNo,Integer pageSize,ApiParam apiParam) throws IllegalAccessException {
        StringBuffer sql = new StringBuffer();
        sql.append("select * from h2_api_param \n");
        List<String[]> condition = SqlUtil.getNotNullField(apiParam);
        //找到对第三方结账时的价格
        sql.append("where ");
        if(condition.size() != 0){
            sql.append(getWhere(condition));
        }
        sql.append( " 1=1 "+" limit " + ((pageNo -1) * pageSize)  + "," + pageSize);
        return sql.toString();
    }

    public String countPageList(ApiParam apiParam) throws IllegalAccessException {
        StringBuffer sql = new StringBuffer();
        sql.append("select count(1) from h2_api_param\n");
        sql.append("where ");
        List<String[]> condition = SqlUtil.getNotNullField(apiParam);
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
    public String updateApiParamByApiParamExample(ApiParam apiParam) throws IllegalAccessException {

        List<String[]> condition = SqlUtil.getNotNullField(apiParam);
        StringBuffer sql = new StringBuffer();
        sql.append("update h2_api_param set \n");
        sql.append(getSet(condition));
        sql.append(" where api_param_id ="+"\""+apiParam.getApi_param_id()+"\"");
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
    public   String insertApiParam(ApiParam apiParam) throws IllegalAccessException {
        List<String[]> condition = SqlUtil.getNotNullField(apiParam);
        StringBuffer sql = new StringBuffer();
        sql.append("insert into h2_api_param(");
        sql.append(getApiParamColumn(condition));
        sql.append(") values (");
        sql.append(getApiParamValues(condition)+")");
        return sql.toString();
    }
    private  String getApiParamColumn(List<String[]> condition){
        StringBuffer columns = new StringBuffer();
        int len = condition.size();
        for(int i = 0; i < len-1; i++){
            columns.append(condition.get(i)[0] +  " ,");
        }
        columns.append(condition.get(len-1)[0] );
        return  columns.toString();
    }
    private String getApiParamValues(List<String[]> condition){
        StringBuffer values=new StringBuffer();
        for(int i = 0; i < condition.size()-1; i++){
            values.append(condition.get(i)[1] +  " ,");
        }
        values.append(condition.get(condition.size()-1)[1]);
        return values.toString();
    }
    public static void main(String[] args) throws IllegalAccessException {

    }
}
