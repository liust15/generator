package com.liust.server.model;

import com.liust.server.util.DBUtil;

import lombok.Data;

/**
 * @program: springdemo
 * @description: 单表信息
 * @author: liust
 * @create: 2019-04-04 19:37
 **/
@Data
public class TableModel {
    private String className;
    private String tableName;
    private String tableComment;

    public void setTableName(String tableName) {
        this.tableName = tableName;
        this.className = DBUtil.upperCase(getClassName(tableName));
    }

    private static String getClassName(String tableName) {
        if (tableName.length() <= 2) return tableName;
        if (tableName.substring(0, 2).equals("t_")) {
            tableName = tableName.substring(2);
        }
        return DBUtil.UnderlineToHump(tableName);
    }
}
