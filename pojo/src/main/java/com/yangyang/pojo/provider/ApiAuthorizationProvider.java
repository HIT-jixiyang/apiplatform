package com.yangyang.pojo.provider;

import com.yangyang.pojo.entity.Api;
import com.yangyang.pojo.entity.ApiAuthorization;
import com.yangyang.utils.utils.SqlUtil;

import java.util.List;

/**
 * @program: apiplatform
 * @description:
 * @author: JiXiYang
 * @create: 2018-05-11 15:40
 **/
public class ApiAuthorizationProvider {
    public String getApiCategoryListPageByAppID(Integer pageNo,Integer pageSize,String app_id){
      StringBuffer sql=new StringBuffer();
      sql.append("SELECT * FROM  h2_api_category WHERE api_category.api_category_id IN (SELECT api_category_id FROM api_app WHERE app_id="+app_id+")");
      sql.append(" limit " + ((pageNo -1) * pageSize)  + "," + pageSize);
        return sql.toString();
    }
    public String getApiCategoryCountByAppID(String app_id){
        StringBuffer sql=new StringBuffer();
        sql.append("SELECT count(*) FROM  h2_api_category WHERE api_category.api_category_id IN (SELECT api_category_id FROM api_app WHERE app_id="+app_id+")");
        return sql.toString();
    }
    public String getApiAuthorizationListByApiAuthorizationExample(ApiAuthorization ApiAuthorization) throws IllegalAccessException {
        StringBuffer sql = new StringBuffer();
        sql.append("select * from h2_api_app \n");
        List<String[]> condition = SqlUtil.getNotNullField(ApiAuthorization);
        //获得授权信息
        sql.append("where ");
        if(condition.size() != 0){
            sql.append(getWhere(condition));
        }
        sql.append( " 1=1");
        return sql.toString();
    }
    //获取api授权表的某一页内容
    public String getApiAuthorizationPageListByApiAuthorizationExample(Integer pageNo, Integer pageSize, ApiAuthorization ApiAuthorization) throws IllegalAccessException {
        StringBuffer sql = new StringBuffer();
        sql.append("select * from h2_api_app \n");
        List<String[]> condition = SqlUtil.getNotNullField(ApiAuthorization);
        sql.append("where ");
        if(condition.size() != 0){
            sql.append(getWhere(condition));
        }
        sql.append( " 1=1");
        return sql.toString() + " limit " + ((pageNo -1) * pageSize)  + "," + pageSize;
    }
//获取api类的页数
    public String countPageList(ApiAuthorization ApiAuthorization) throws IllegalAccessException {
        StringBuffer sql = new StringBuffer();
        sql.append("select count(1) from h2_api_app\n");
        sql.append("where ");
        List<String[]> condition = SqlUtil.getNotNullField(ApiAuthorization);
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
    public String updateApiAuthorizationByApiExample(ApiAuthorization ApiAuthorization) throws IllegalAccessException {

        List<String[]> condition = SqlUtil.getNotNullField(ApiAuthorization);
        StringBuffer sql = new StringBuffer();
        sql.append("update h2_api_app set \n");
        sql.append(getSet(condition));
        sql.append(" where api_category_id ="+"\""+ApiAuthorization.getApi_category_id()+"\""+" and app_id="+"\""+ApiAuthorization.getApp_id()+"\"");
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
    public   String insertApiAuthorization(ApiAuthorization ApiAuthorization) throws IllegalAccessException {
        List<String[]> condition = SqlUtil.getNotNullField(ApiAuthorization);
        StringBuffer sql = new StringBuffer();
        sql.append("insert into h2_api_app(");
        sql.append(getApiAuthorizationColumn(condition));
        sql.append(") values (");
        sql.append(getApiAuthorizationValues(condition)+")");
        return sql.toString();
    }
    private  String getApiAuthorizationColumn(List<String[]> condition){
        StringBuffer columns = new StringBuffer();
        int len = condition.size();
        for(int i = 0; i < len-1; i++){
            columns.append(condition.get(i)[0] +  " ,");
        }
        columns.append(condition.get(len-1)[0] );
        return  columns.toString();
    }
    private String getApiAuthorizationValues(List<String[]> condition){
        StringBuffer values=new StringBuffer();
        for(int i = 0; i < condition.size()-1; i++){
            values.append(condition.get(i)[1] +  " ,");
        }
        values.append(condition.get(condition.size()-1)[1]);
        return values.toString();
    }

    public static void main(String[] args) {
        System.out.println(new ApiAuthorizationProvider().getApiCategoryListPageByAppID(1,10,"4"));
    }
}
