package com.yangyang.pojo.provider;

import com.yangyang.pojo.entity.LeafMap;
import com.yangyang.utils.utils.SqlUtil;

import java.util.List;

/**
 * @Author: ny
 * @Date: Created in 9:25 2018/4/7 0007
 */
public class LeafMapProvider {
    public String getLeafMapListByLeafMapExample(LeafMap leafMap) throws IllegalAccessException {
        StringBuffer sql = new StringBuffer();
        sql.append("select * from h2_leafmap \n");
        List<String[]> condition = SqlUtil.getNotNullField(leafMap);
        sql.append("where ");
        if(condition.size() != 0){
            sql.append(getWhere(condition));
        }
        sql.append( " 1=1 ");
        System.out.println(sql);
        return sql.toString();
    }


    private String getWhere(List<String[]> condition){
        StringBuffer where = new StringBuffer();
        for(int i = 0, len = condition.size(); i < len; i++){
            where.append(condition.get(i)[0] + "=" + condition.get(i)[1] + " and");
        }
        return where.toString();
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
public   String insertLeafMap(LeafMap leafMap) throws IllegalAccessException {
    List<String[]> condition = SqlUtil.getNotNullField(leafMap);
    StringBuffer sql = new StringBuffer();
    sql.append("insert into h2_leafmap(");
    sql.append(getLeafMapColumn(condition));
    sql.append(") values (");
    sql.append(getLeafMapValues(condition)+")");
    return sql.toString();
}
private  String getLeafMapColumn(List<String[]> condition){
    StringBuffer columns = new StringBuffer();
    int len = condition.size();
    for(int i = 0; i < len-1; i++){
        columns.append(condition.get(i)[0] +  " ,");
    }
    columns.append(condition.get(len-1)[0] );
    return  columns.toString();
}
private String getLeafMapValues(List<String[]> condition){
StringBuffer values=new StringBuffer();
    for(int i = 0; i < condition.size()-1; i++){
        values.append(condition.get(i)[1] +  " ,");
    }
    values.append(condition.get(condition.size()-1)[1]);
    return values.toString();
}
    public static void main(String[] args) throws IllegalAccessException {
       LeafMap leafMap=new LeafMap();
       leafMap.setApi_id("11111111111111");
      //  System.out.println(new LeafMapMapProvider().getLeafMapListByLeafMapExample(leafMap));
      //  System.out.println(new LeafMapProvider().insertLeafMap(leafMap));
    }
}
