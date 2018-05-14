package com.yangyang.pojo.provider;

import com.yangyang.pojo.entity.Api;
import com.yangyang.pojo.entity.ApiCategory;
import com.yangyang.utils.utils.SqlUtil;

import java.util.List;

/**
 * @program: apiplatform
 * @description:
 * @author: JiXiYang
 * @create: 2018-05-11 15:40
 **/
public class ApiCategoryProvider {
    public String getApiCategoryListByApiCategoryExample(ApiCategory apiCategory) throws IllegalAccessException {
        StringBuffer sql = new StringBuffer();
        sql.append("select * from api_category \n");
        List<String[]> condition = SqlUtil.getNotNullField(apiCategory);
        //找到对第三方结账时的价格
        sql.append("where ");
        if(condition.size() != 0){
            sql.append(getWhere(condition));
        }
        sql.append( " 1=1");
        return sql.toString();
    }
    //获取api类的某一页内容
    public String getApiCategoryPageListByApiCategoryExample(Integer pageNo, Integer pageSize, ApiCategory apiCategory) throws IllegalAccessException {
        StringBuffer sql = new StringBuffer();
        sql.append("select * from api_category \n");
        List<String[]> condition = SqlUtil.getNotNullField(apiCategory);
        sql.append("where ");
        if(condition.size() != 0){
            sql.append(getWhere(condition));
        }
        sql.append( " 1=1");
        return sql.toString() + " limit " + ((pageNo -1) * pageSize)  + "," + pageSize;
    }
//获取api类的页数
    public String countPageList(ApiCategory apiCategory) throws IllegalAccessException {
        StringBuffer sql = new StringBuffer();
        sql.append("select count(1) from api_category\n");
        sql.append("where ");
        List<String[]> condition = SqlUtil.getNotNullField(apiCategory);
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
    public String updateApiCategoryByApiExample(ApiCategory apiCategory) throws IllegalAccessException {

        List<String[]> condition = SqlUtil.getNotNullField(apiCategory);
        StringBuffer sql = new StringBuffer();
        sql.append("update api_category set \n");
        sql.append(getSet(condition));
        sql.append(" where api_category_id ="+"\""+apiCategory.getApi_category_id()+"\"");
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
    public   String insertApiCategory(ApiCategory apiCategory) throws IllegalAccessException {
        List<String[]> condition = SqlUtil.getNotNullField(apiCategory);
        StringBuffer sql = new StringBuffer();
        sql.append("insert into apicategory(");
        sql.append(getApiCategoryColumn(condition));
        sql.append(") values (");
        sql.append(getApiCategoryValues(condition)+")");
        return sql.toString();
    }
    private  String getApiCategoryColumn(List<String[]> condition){
        StringBuffer columns = new StringBuffer();
        int len = condition.size();
        for(int i = 0; i < len-1; i++){
            columns.append(condition.get(i)[0] +  " ,");
        }
        columns.append(condition.get(len-1)[0] );
        return  columns.toString();
    }
    private String getApiCategoryValues(List<String[]> condition){
        StringBuffer values=new StringBuffer();
        for(int i = 0; i < condition.size()-1; i++){
            values.append(condition.get(i)[1] +  " ,");
        }
        values.append(condition.get(condition.size()-1)[1]);
        return values.toString();
    }
}
