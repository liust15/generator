package com.liust.server.model;

import lombok.Data;


/**
 * @program: springdemo
 * @description:
 * @author: liust
 * @create: 2019-04-04 17:50
 **/
@Data
public class DBConfigModel {
    private String ip;
    private String dbName;
    private String user;
    private String password;
    private String url;
    private String group;
    public static final String DBCONN = "jdbc:mysql://";
    private final static String DBCONFIG = "?useUnicode=true&characterEncoding=utf-8&useSSL=true&serverTimezone=UTC&autoReconnect=true&failOverReadOnly=false";


    public String getUrl() {
        this.url = DBCONN + this.ip + "/" + this.dbName + DBCONFIG;
        return this.url;
    }

    public String getGroup() {
        return group+"." + this.getDbName();
    }
}
