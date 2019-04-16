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


    public JdbcTemplate getJdbcTemplate(DBConfigModel dbConfigModel) {
        DriverManagerDataSource driverManagerDataSource = new DriverManagerDataSource();
        driverManagerDataSource.setDriverClassName("com.mysql.jdbc.Driver");
        driverManagerDataSource.setUrl(dbConfigModel.getUrl());
        driverManagerDataSource.setUsername(dbConfigModel.getUser());
        driverManagerDataSource.setPassword(dbConfigModel.getPassword());
        return new JdbcTemplate(driverManagerDataSource);
    }



}