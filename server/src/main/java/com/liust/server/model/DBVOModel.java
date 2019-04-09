package com.liust.server.model;

import lombok.Data;

/**
 * @program: server
 * @description:
 * @author: liust
 * @create: 2019-04-06 09:55
 **/
@Data
public class DBVOModel {
    //数据库信息
    private DBConfigModel dbConfigModel;
    //表名
    private TableModel tableModel;
    //全表下载or单表下载
    private int attributes;
}
