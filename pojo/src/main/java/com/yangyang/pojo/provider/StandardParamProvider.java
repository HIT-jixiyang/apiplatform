package com.yangyang.pojo.provider;

import com.yangyang.pojo.entity.StandardInboundParam;
import com.yangyang.utils.utils.SqlUtil;

import java.util.List;

/**
 * @program: apiplatform
 * @description:
 * @author: JiXiYang
 * @create: 2018-05-11 15:40
 **/
public class StandardParamProvider {
    //获取所有api类
    public String getStandardInboundParamListByStandardInboundParamExample(StandardInboundParam standardInboundParam) throws IllegalAccessException {
        StringBuffer sql = new StringBuffer();
        sql.append("select * from h2_standard_inbound_param \n");
        List<String[]> condition = SqlUtil.getNotNullField(standardInboundParam);
        //找到对第三方结账时的价格
        sql.append("where ");
        if(condition.size() != 0){
            sql.append(getWhere(condition));
        }
        sql.append( " 1=1");
        return sql.toString();
    }
    //获取api类的某一页内容
    private String getWhere(List<String[]> condition){
        StringBuffer where = new StringBuffer();
        for(int i = 0, len = condition.size(); i < len; i++){
            where.append(condition.get(i)[0] + "=" + condition.get(i)[1] + " and");
        }
        return where.toString();
    }
    public   String insertStandardInboundParam(StandardInboundParam standardInboundParam) throws IllegalAccessException {
        List<String[]> condition = SqlUtil.getNotNullField(standardInboundParam);
        StringBuffer sql = new StringBuffer();
        sql.append("insert into h2_standard_inbound_param(");
        sql.append(getStandardInboundParamColumn(condition));
        sql.append(") values (");
        sql.append(getStandardInboundParamValues(condition)+")");
        return sql.toString();
    }
    private  String getStandardInboundParamColumn(List<String[]> condition){
        StringBuffer columns = new StringBuffer();
        int len = condition.size();
        for(int i = 0; i < len-1; i++){
            columns.append(condition.get(i)[0] +  " ,");
        }
        columns.append(condition.get(len-1)[0] );
        return  columns.toString();
    }
    private String getStandardInboundParamValues(List<String[]> condition){
        StringBuffer values=new StringBuffer();
        for(int i = 0; i < condition.size()-1; i++){
            values.append(condition.get(i)[1] +  " ,");
        }
        values.append(condition.get(condition.size()-1)[1]);
        return values.toString();
    }
}
