package com.liust.server.config;

import com.liust.server.model.DBConfigModel;
import com.liust.server.model.DBModel;
import com.liust.server.model.TableInfo;
import com.liust.server.model.TableModel;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.datasource.DriverManagerDataSource;
import org.springframework.stereotype.Component;

import java.util.List;

import lombok.extern.slf4j.Slf4j;

/**
 * @program: springdemo
 * @description:
 * @author: liust
 * @create: 2019-04-04 19:58
 **/

public abstract class DBConfig {
    protected JdbcTemplate jdbcTemplate;
    private static final String MYSQL_JDBC_DRIVER="com.mysql.jdbc.Driver";
    protected static final String QUERY_TABLES = "SELECT distinct table_schema as dbName FROM information_schema.TABLES";
    protected static final String QUERY_TABLE = "SELECT * FROM information_schema.TABLES WHERE table_schema= ?";
    protected static final String QUERY_TABLE_INFO = "SELECT column_name,column_comment,data_type,column_key FROM information_schema.COLUMNS WHERE table_schema = ? AND table_name = ? ";

    public void setJdbcTemplate(DBConfigModel dbConfigModel) {
        DriverManagerDataSource driverManagerDataSource = new DriverManagerDataSource();
        driverManagerDataSource.setDriverClassName(MYSQL_JDBC_DRIVER);
        driverManagerDataSource.setUrl(dbConfigModel.getUrl());
        driverManagerDataSource.setUsername(dbConfigModel.getUser());
        driverManagerDataSource.setPassword(dbConfigModel.getPassword());
        this.jdbcTemplate =   new JdbcTemplate(driverManagerDataSource);
    }

    public abstract List<DBModel> getDbModel(DBConfigModel dbConfigModel);

    public abstract List<TableModel> getTableModel(DBConfigModel dbConfigModel);

    public abstract List<TableInfo> getTableInfo(DBConfigModel dbConfigModel, TableModel tableModel);
}