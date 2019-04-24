package com.liust.server.config;

import com.liust.server.model.DBConfigModel;
import com.liust.server.model.DBModel;
import com.liust.server.model.TableInfo;
import com.liust.server.model.TableModel;

import java.util.List;

/**
 * @program: generator
 * @description:
 * @author: liust
 * @create: 2019-04-17 21:36
 **/
public interface IDBConfig {

    List<DBModel> getDbModel(DBConfigModel dbConfigModel);

    List<TableModel> getTableModel(DBConfigModel dbConfigModel);

    List<TableInfo> getTableInfo(DBConfigModel dbConfigModel, TableModel tableModel);

}
