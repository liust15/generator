package com.liust.server.service;


import com.liust.server.model.DBConfigModel;
import com.liust.server.model.DBModel;
import com.liust.server.model.DBVOModel;
import com.liust.server.model.TableFileInfoModel;
import com.liust.server.model.TableModel;

import java.util.List;
import java.util.zip.ZipOutputStream;

/**
 * @program: springdemo
 * @description:
 * @author: liust
 * @create: 2019-04-04 17:47
 **/
public interface DBServer {
     List<DBModel> getDBs(DBConfigModel dbConfigModel);
    //获取全部表名
    List<TableModel> getDBTables(DBConfigModel dbConfigModel);

    List<TableFileInfoModel> getTableInfo(DBVOModel dbvoModel);

    void tablesToZip(DBVOModel dbvoModel, ZipOutputStream zos);
}
