package com.yangyang.pojo.provider;

import com.yangyang.pojo.entity.Leaf;
import com.yangyang.utils.utils.SqlUtil;

import java.util.List;

/**
 * @Author: ny
 * @Date: Created in 9:25 2018/4/7 0007
 */
public class LeafProvider {
    public String getLeafListByLeafExample( Leaf leaf) throws IllegalAccessException {
        StringBuffer sql = new StringBuffer();
        sql.append("select * from h2_leaf \n");
        List<String[]> condition = SqlUtil.getNotNullField(leaf);
        sql.append("where ");
        if(condition.size() != 0){
            sql.append(getWhere(condition));
        }
        sql.append( " 1=1 "+ " order by leaf_id");
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
    public String updateLeafByLeafExample(Leaf leaf) throws IllegalAccessException {

        List<String[]> condition = SqlUtil.getNotNullField(leaf);
        StringBuffer sql = new StringBuffer();
        sql.append("update h2_leaf set \n");
        sql.append(getSet(condition));
        sql.append(" where leaf_id ="+"\""+leaf.getLeaf_id()+"\"");
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
public   String insertLeaf(Leaf leaf) throws IllegalAccessException {
    List<String[]> condition = SqlUtil.getNotNullField(leaf);
    StringBuffer sql = new StringBuffer();
    sql.append("insert into h2_leaf(");
    sql.append(getLeafColumn(condition));
    sql.append(") values (");
    sql.append(getLeafValues(condition)+")");
    return sql.toString();
}
private  String getLeafColumn(List<String[]> condition){
    StringBuffer columns = new StringBuffer();
    int len = condition.size();
    for(int i = 0; i < len-1; i++){
        columns.append(condition.get(i)[0] +  " ,");
    }
    columns.append(condition.get(len-1)[0] );
    return  columns.toString();
}
private String getLeafValues(List<String[]> condition){
StringBuffer values=new StringBuffer();
    for(int i = 0; i < condition.size()-1; i++){
        values.append(condition.get(i)[1] +  " ,");
    }
    values.append(condition.get(condition.size()-1)[1]);
    return values.toString();
}
    public static void main(String[] args) throws IllegalAccessException {
       Leaf leaf=new Leaf();
       leaf.setApi_id("11111111111111");
        System.out.println(new LeafProvider().getLeafListByLeafExample(leaf));
      //  System.out.println(new LeafProvider().insertLeaf(leaf));
    }
}
