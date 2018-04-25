package com.yangyang.apiplatform.provider;

import com.yangyang.apiplatform.entity.Api;
import com.yangyang.apiplatform.utils.SqlUtil;

import java.util.List;

/**
 * @Author: ny
 * @Date: Created in 9:25 2018/4/7 0007
 */
public class ApiProvider {
    public String getApiPageListByApiExample(Integer pageNo, Integer pageSize, Api api) throws IllegalAccessException {
        StringBuffer sql = new StringBuffer();
        sql.append("select a.*,s.sp_name from api a,service_provider s\n");
        List<String[]> condition = SqlUtil.getNotNullField(api);
        sql.append("where ");
        if(condition.size() != 0){
            sql.append(getWhere(condition));
        }
        sql.append(" a.sp_id=s.sp_id and");
        sql.append( " 1=1");
        return sql.toString() + " limit " + ((pageNo -1) * pageSize)  + "," + pageSize;
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
}
