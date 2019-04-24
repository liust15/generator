package com.liust.server.service.impl;


import com.liust.server.dao.DBDao;
import com.liust.server.enums.DownloadEnum;
import com.liust.server.enums.TemplatesEnum;
import com.liust.server.model.DBConfigModel;
import com.liust.server.model.DBModel;
import com.liust.server.model.DBVOModel;
import com.liust.server.model.DomainModel;
import com.liust.server.model.TableFileInfoModel;
import com.liust.server.model.TableInfo;
import com.liust.server.model.TableModel;
import com.liust.server.service.DBServer;
import com.liust.server.util.DBUtil;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.ui.freemarker.FreeMarkerTemplateUtils;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.zip.ZipOutputStream;

import freemarker.template.Configuration;
import freemarker.template.TemplateException;
import lombok.extern.slf4j.Slf4j;

import static com.liust.server.util.FileUtil.compress;

/**
 * @program: springdemo
 * @description:
 * @author: liust
 * @create: 2019-04-04 17:48
 **/
@Service
@Slf4j
public class DBServerImpl implements DBServer {
    private final DBDao dbDao;
    private final Configuration configuration;

    public DBServerImpl(Configuration configuration, DBDao dbDao) {
        this.configuration = configuration;
        this.dbDao = dbDao;
    }

    @Override
    public List<DBModel> getDBs(DBConfigModel dbConfigModel) {
        return this.dbDao.getDbModel(dbConfigModel);
    }

    @Override
    public List<TableModel> getDBTables(DBConfigModel dbConfigModel) {
        return this.dbDao.getTableModel(dbConfigModel);
    }

    @Override
    public List<TableFileInfoModel> getTableInfo(DBVOModel dbvoModel) {
        List<TableFileInfoModel> list = new ArrayList<>();
        if (dbvoModel.getAttributes() == DownloadEnum.SINGLETABLE.getAttributes()) {
            list.add(getTableFileInfo(dbvoModel.getDbConfigModel(), dbvoModel.getTableModel()));
        } else {
            this.dbDao.getTableModel(dbvoModel.getDbConfigModel())
                    .forEach(tableModel -> list.add(getTableFileInfo(dbvoModel.getDbConfigModel(), tableModel)));
        }
        return list;
    }

    private TableFileInfoModel getTableFileInfo(DBConfigModel dbConfigModel, TableModel tableModel) {
        List<TableInfo> tableInfo = this.dbDao.getTableInfo(dbConfigModel, tableModel);
        DomainModel domainModel = new DomainModel();
        domainModel.setPackageName(dbConfigModel.getGroup());
        domainModel.setTableInfos(analysis(tableInfo));
        tableModel.setTableName(tableModel.getTableName());
        domainModel.setTableModel(tableModel);
        return getTableInfo(domainModel);
    }

    /**
     * 压缩成ZIP 方法1
     *
     * @param dbvoModel 压缩文件夹路径
     *             false:所有文件跑到压缩包根目录下(注意：不保留目录结构可能会出现同名文件,会压缩失败)
     * @throws RuntimeException 压缩失败会抛出运行时异常
     */
    @Override
    public void tablesToZip(DBVOModel dbvoModel, ZipOutputStream zos) {
        String packages = toPath("java." + dbvoModel.getDbConfigModel().getGroup());
        List<TableFileInfoModel> tablesInfo = getTableInfo(dbvoModel);
        tablesInfo.forEach(tableFileInfoModel -> createZipByTable(zos, tableFileInfoModel, packages));
    }

    private TableFileInfoModel getTableInfo(DomainModel domainModel) {
        return TableFileInfoModel.builder()
                                 .domain(getTemplate(TemplatesEnum.DOMAIN, domainModel))
                                 .mapperInter(getTemplate(TemplatesEnum.MAPPERINTERFACE, domainModel))
                                 .service(getTemplate(TemplatesEnum.DOMAINSERVER, domainModel))
                                 .serviceImpl(getTemplate(TemplatesEnum.DOMAINSERVERIMPL, domainModel))
                                 .mapperXml(getTemplate(TemplatesEnum.MAPPERXML, domainModel))
                                 .tableName(domainModel.getTableModel().getClassName())
                                 .build();

    }

    private void createZipByTable(ZipOutputStream zos, TableFileInfoModel tableInfo, String packages) {
        try {
            compress(zos, tableInfo.getDomain(), createFileName(TemplatesEnum.DOMAIN, packages, tableInfo.getTableName()));
            compress(zos, tableInfo.getMapperInter(), createFileName(TemplatesEnum.MAPPERINTERFACE, packages,  tableInfo.getTableName()));
            compress(zos, tableInfo.getMapperXml(), createFileName(TemplatesEnum.MAPPERXML, "",  tableInfo.getTableName()));
            compress(zos, tableInfo.getService(), createFileName(TemplatesEnum.DOMAINSERVER, packages,  tableInfo.getTableName()));
            compress(zos, tableInfo.getServiceImpl(), createFileName(TemplatesEnum.DOMAINSERVERIMPL, packages,  tableInfo.getTableName()));
        } catch (IOException e) {
            log.error("压缩生成失败 {}", e.getMessage());
            throw new RuntimeException("压缩生成失败 " + e.getMessage());
        }
    }

    private String toPath(String s) {
        return s.replaceAll("\\.", "/");
    }

    private String createFileName(TemplatesEnum templatesEnum, String packages, String tableName) {
        return packages + templatesEnum.getFliePath() + tableName + templatesEnum.getFileSuffix();
    }


    public String getTemplate(TemplatesEnum templatesEnum, DomainModel domainModel) {
        try {
            return FreeMarkerTemplateUtils.processTemplateIntoString(configuration.getTemplate(templatesEnum.getTemplateName()), domainModel);
        } catch (IOException | TemplateException e) {
            log.error("模板生成失败 {}", e.getMessage());
            throw new RuntimeException("模板生成失败 " + e.getMessage());
        }
    }


    private List<TableInfo> analysis(List<TableInfo> list) {
        list.forEach(tableInfo ->{
            tableInfo.setType(DBUtil.toSqlToJava(tableInfo.getType()));
            tableInfo.setField(DBUtil.UnderlineToHump(tableInfo.getField()));
        });
        return list;
    }

}
