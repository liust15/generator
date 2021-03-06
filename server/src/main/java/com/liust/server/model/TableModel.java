package com.liust.server.model;

import com.liust.server.util.DBUtil;

import java.io.Serializable;

/**
 * @program: springdemo
 * @description: 单表信息
 * @author: liust
 * @create: 2019-04-04 19:37
 **/
public class TableModel implements Serializable {
    private String className;
    private String tableName;
    private String tableComment;

    public void setTableName(String tableName) {
        this.tableName = tableName;
        this.setClassName(DBUtil.upperCase(getClassName(tableName)));
    }

    private String getClassName(String tableName){
        if (tableName.length() <= 2) return tableName;
        if (tableName.substring(0, 2).equals("t_")) {
            tableName = tableName.substring(2);
        }
        return DBUtil.UnderlineToHump(tableName);
    }

    public void setClassName(String className) {
        this.className = className;
    }

    public void setTableComment(String tableComment) {
        this.tableComment = tableComment;
    }

    public String getClassName() {
        return className;
    }

    public String getTableName() {
        return tableName;
    }

    public String getTableComment() {
        return tableComment;
    }
}
