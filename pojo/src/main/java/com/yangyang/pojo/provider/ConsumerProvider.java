package com.yangyang.pojo.provider;

import com.yangyang.pojo.entity.Consumer;
import com.yangyang.utils.utils.SqlUtil;
import java.util.List;
/**
 * @program: Consumerplatform
 * @description:
 * @author: JiXiYang
 * @create: 2018-05-14 10:35
 **/
public class ConsumerProvider {
    public String getConsumerPageListByConsumerExample(Integer pageNo, Integer pageSize, Consumer consumer,String key) throws IllegalAccessException {
        StringBuffer sql = new StringBuffer();
        sql.append("select * from h2_consumer \n");
        List<String[]> condition = SqlUtil.getNotNullField(consumer);
        sql.append("where ");
        if(condition.size() != 0){
            sql.append(getWhere(condition));
        }
        if(key!=null&&key!="")
        sql.append(" ( consumer_name like '%"+key+"%' or consumer_intro like '%"+key+"%' ) and ");
        sql.append( " 1=1 order by consumer_create_time desc ");
        return sql.toString() + " limit " + ((pageNo -1) * pageSize)  + "," + pageSize;
    }



    public String countPageList(Consumer consumer,String key) throws IllegalAccessException {
        StringBuffer sql = new StringBuffer();
        sql.append("select count(*) from h2_consumer \n");
        List<String[]> condition = SqlUtil.getNotNullField(consumer);
        sql.append("where ");
        if(condition.size() != 0){
            sql.append(getWhere(condition));
        }
        if(key!=null&&key!="")
        sql.append(" ( consumer_name like '%"+key+"%' or consumer_intro like '%"+key+"%' ) and ");
        sql.append( " 1=1");
        return sql.toString();
    }

    private String getWhere(List<String[]> condition){
        StringBuffer where = new StringBuffer();
        for(int i = 0, len = condition.size(); i < len; i++){
            where.append(condition.get(i)[0] + "=" + condition.get(i)[1] + " and");
        }
        return where.toString();
    }
    public String updateConsumerByConsumerExample(Consumer consumer) throws IllegalAccessException {

        List<String[]> condition = SqlUtil.getNotNullField(consumer);
        StringBuffer sql = new StringBuffer();
        sql.append("update h2_consumer set \n");
        sql.append(getSet(condition));
        sql.append(" where consumer_id ="+"\""+consumer.getConsumer_id()+"\"");
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
    public   String insertConsumer(Consumer Consumer) throws IllegalAccessException {
        List<String[]> condition = SqlUtil.getNotNullField(Consumer);
        StringBuffer sql = new StringBuffer();
        sql.append("insert into h2_consumer(");
        sql.append(getConsumerColumn(condition));
        sql.append(") values (");
        sql.append(getConsumerValues(condition)+")");
        return sql.toString();
    }
    private  String getConsumerColumn(List<String[]> condition){
        StringBuffer columns = new StringBuffer();
        int len = condition.size();
        for(int i = 0; i < len-1; i++){
            columns.append(condition.get(i)[0] +  " ,");
        }
        columns.append(condition.get(len-1)[0] );
        return  columns.toString();
    }
    private String getConsumerValues(List<String[]> condition){
        StringBuffer values=new StringBuffer();
        for(int i = 0; i < condition.size()-1; i++){
            values.append(condition.get(i)[1] +  " ,");
        }
        values.append(condition.get(condition.size()-1)[1]);
        return values.toString();
    }
    public static void main(String[] args) throws IllegalAccessException {
        Consumer Consumer=new Consumer();

        System.out.println(new ConsumerProvider().getConsumerPageListByConsumerExample(1,90,Consumer,"Y"));
    }
}
