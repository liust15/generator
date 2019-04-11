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
@Component
@Slf4j
public class DBConfig {

    private JdbcTemplate jdbcTemplate;

    private JdbcTemplate getJdbcTemplate(DBConfigModel dbConfigModel) {
        DriverManagerDataSource driverManagerDataSource = new DriverManagerDataSource();
        driverManagerDataSource.setDriverClassName("com.mysql.jdbc.Driver");
        driverManagerDataSource.setUrl(dbConfigModel.getUrl());
        driverManagerDataSource.setUsername(dbConfigModel.getUser());
        driverManagerDataSource.setPassword(dbConfigModel.getPassword());
        return new JdbcTemplate(driverManagerDataSource);
    }

    public List<DBModel> getDbModel(DBConfigModel dbConfigModel) {
        this.jdbcTemplate = getJdbcTemplate(dbConfigModel);
        List<DBModel> list;
        try {
            list = this.jdbcTemplate.query("SELECT table_schema FROM information_schema.TABLES GROUP BY table_schema", (resultSet, i) -> {
                DBModel dbModel = new DBModel();
                dbModel.setDbName(resultSet.getString("table_schema"));
                return dbModel;
            });
        } catch (Exception e) {
            log.error("账号错误 {}", e.getMessage());
            throw new RuntimeException("数据库连接失败:{}", e);
        }
        return list;
    }


    public List<TableModel> getTableModel(DBConfigModel dbConfigModel) {
        List<TableModel> list;
        try {
            if (this.jdbcTemplate == null) this.jdbcTemplate = getJdbcTemplate(dbConfigModel);
            list = jdbcTemplate.query("SELECT * FROM information_schema.TABLES WHERE table_schema= ?", (resultSet, i) -> {
                TableModel tableModel = new TableModel();
                tableModel.setTableComment(resultSet.getString("table_comment"));
                tableModel.setTableName(resultSet.getString("table_name"));
                return tableModel;
            }, dbConfigModel.getDbName());
        } catch (Exception e) {
            log.error("账号错误 {}", e.getMessage());
            throw new RuntimeException("数据库连接失败:{}", e);
        }
        return list;
    }

    public List<TableInfo> getTableInfo(DBConfigModel dbConfigModel, TableModel tableModel) {
        List<TableInfo> list = null;
        try {
            JdbcTemplate jdbcTemplate = getJdbcTemplate(dbConfigModel);
            list = jdbcTemplate.query("SELECT column_name,column_comment,data_type,column_key FROM information_schema.COLUMNS WHERE table_schema=? AND table_name=?", (resultSet, i) -> {
                TableInfo tableInfo = new TableInfo();
                tableInfo.setField(resultSet.getString("column_name"));
                tableInfo.setKey(resultSet.getString("column_key"));
                tableInfo.setType(resultSet.getString("data_type"));
                tableInfo.setComment(resultSet.getString("column_comment"));
                return tableInfo;
            }, dbConfigModel.getDbName(), tableModel.getTableName());
        } catch (Exception e) {
            e.printStackTrace();
        }
        return list;
    }

}