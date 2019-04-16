package com.liust.server.dao;

import com.liust.server.config.DBConfig;
import com.liust.server.model.DBConfigModel;
import com.liust.server.model.DBModel;
import com.liust.server.model.TableInfo;
import com.liust.server.model.TableModel;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.List;

import lombok.extern.slf4j.Slf4j;

/**
 * @program: generator
 * @description:
 * @author: liust
 * @create: 2019-04-16 10:16
 **/
@Slf4j
@Repository
public class DBDao {
    @Autowired
    private DBConfig dbConfig;
    private JdbcTemplate jdbcTemplate;
    private void init(DBConfigModel dbConfigModel){
        this.jdbcTemplate = dbConfig.getJdbcTemplate(dbConfigModel);
    }

    public List<DBModel> getDbModel(DBConfigModel dbConfigModel) {
       this.init(dbConfigModel);
        List<DBModel> list;
        try {
            list = this.jdbcTemplate.query("SELECT distinct table_schema as dbName FROM information_schema.TABLES", (resultSet, i) -> {
                DBModel dbModel = new DBModel();
                dbModel.setDbName(resultSet.getString("dbName"));
                return dbModel;
            });
        } catch (Exception e) {
            log.error("账号错误 {}", e.getMessage());
            throw new RuntimeException("数据库连接失败:{}", e);
        }
        return list;
    }


    public List<TableModel> getTableModel(DBConfigModel dbConfigModel) {
        this.init(dbConfigModel);
        List<TableModel> list;
        try {
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
        this.init(dbConfigModel);
        List<TableInfo> list;
        try {
            list = jdbcTemplate.query("SELECT column_name,column_comment,data_type,column_key FROM information_schema.COLUMNS WHERE table_schema=? AND table_name=?", (resultSet, i) -> {
                TableInfo tableInfo = new TableInfo();
                tableInfo.setField(resultSet.getString("column_name"));
                tableInfo.setKey(resultSet.getString("column_key"));
                tableInfo.setType(resultSet.getString("data_type"));
                tableInfo.setComment(resultSet.getString("column_comment"));
                return tableInfo;
            }, dbConfigModel.getDbName(), tableModel.getTableName());
        } catch (Exception e) {
            log.error("账号错误 {}", e.getMessage());
            throw new RuntimeException("数据库连接失败:{}", e);
        }
        return list;
    }
}
