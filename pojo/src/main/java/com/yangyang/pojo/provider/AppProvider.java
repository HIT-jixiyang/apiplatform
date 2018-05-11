package com.yangyang.pojo.provider;



import com.yangyang.pojo.entity.App;
import com.yangyang.utils.utils.SqlUtil;

import java.util.List;

/**
 * @program: apiplatform
 * @description: App分页
 * @author: JiXiYang
 * @create: 2018-04-16 08:57
 **/
public class AppProvider {

    public String getAppPageList(Integer pageNo, Integer pageSize, App app) throws IllegalAccessException {
        StringBuffer sql = new StringBuffer();
        sql.append("select a.* from app a\n");
        List<String[]> condition = SqlUtil.getNotNullField(app);
        sql.append("where ");
        if(condition.size() != 0){
            sql.append(getWhere(condition));
        }
        sql.append( " 1=1");
        return sql.toString() + " limit " + ((pageNo -1) * pageSize)  + "," + pageSize;
    }

    public String countPageList(App app) throws IllegalAccessException {
        StringBuffer sql = new StringBuffer();
        sql.append("select count(1) from app\n");
        sql.append("where ");
        List<String[]> condition = SqlUtil.getNotNullField(app);
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
