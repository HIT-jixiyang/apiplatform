package com.yangyang.pojo.provider;

import com.yangyang.pojo.entity.Sp;
import com.yangyang.utils.utils.SqlUtil;
import java.util.List;
/**
 * @program: Spplatform
 * @description:
 * @author: JiXiYang
 * @create: 2018-05-14 10:35
 **/
public class SpProvider {
    public String getSpPageListBySpExample(Integer pageNo, Integer pageSize, Sp sp,String key) throws IllegalAccessException {
        StringBuffer sql = new StringBuffer();
        sql.append("select * from service_provider \n");
        List<String[]> condition = SqlUtil.getNotNullField(sp);
        sql.append("where ");
        if(condition.size() != 0){
            sql.append(getWhere(condition));
        }
        if(key!=null&&key!="")
            sql.append(" ( sp_name like '%"+key+"%' or sp_description like '%"+key+"%' ) and ");
        sql.append( " 1=1 order by sp_create_time desc ");
        return sql.toString() + " limit " + ((pageNo -1) * pageSize)  + "," + pageSize;
    }



    public String countPageList(Sp sp,String key) throws IllegalAccessException {
        StringBuffer sql = new StringBuffer();
        sql.append("select count(*) from service_provider \n");
        List<String[]> condition = SqlUtil.getNotNullField(sp);
        sql.append("where ");
        if(condition.size() != 0){
            sql.append(getWhere(condition));
        }
        if(key!=null&&key!="")
            sql.append(" ( sp_name like '%"+key+"%' or sp_description like '%"+key+"%') and ");
        sql.append( " 1=1 order by sp_create_time desc ");
        return sql.toString();
    }

    private String getWhere(List<String[]> condition){
        StringBuffer where = new StringBuffer();
        for(int i = 0, len = condition.size(); i < len; i++){
            where.append(condition.get(i)[0] + "=" + condition.get(i)[1] + " and");
        }
        return where.toString();
    }
    public String updateSpBySpExample(Sp sp) throws IllegalAccessException {

        List<String[]> condition = SqlUtil.getNotNullField(sp);
        StringBuffer sql = new StringBuffer();
        sql.append("update service_provider set \n");
        sql.append(getSet(condition));
        sql.append(" where sp_id ="+"\""+sp.getSp_id()+"\"");
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
    public   String insertSp(Sp sp) throws IllegalAccessException {
        List<String[]> condition = SqlUtil.getNotNullField(sp);
        StringBuffer sql = new StringBuffer();
        sql.append("insert into service_provider(");
        sql.append(getSpColumn(condition));
        sql.append(") values (");
        sql.append(getSpValues(condition)+")");
        return sql.toString();
    }
    private  String getSpColumn(List<String[]> condition){
        StringBuffer columns = new StringBuffer();
        int len = condition.size();
        for(int i = 0; i < len-1; i++){
            columns.append(condition.get(i)[0] +  " ,");
        }
        columns.append(condition.get(len-1)[0] );
        return  columns.toString();
    }
    private String getSpValues(List<String[]> condition){
        StringBuffer values=new StringBuffer();
        for(int i = 0; i < condition.size()-1; i++){
            values.append(condition.get(i)[1] +  " ,");
        }
        values.append(condition.get(condition.size()-1)[1]);
        return values.toString();
    }

    public static void main(String[] args) throws IllegalAccessException {
        Sp sp=new Sp();

        System.out.println(new SpProvider().getSpPageListBySpExample(1,90,sp,"Y"));
    }
}
