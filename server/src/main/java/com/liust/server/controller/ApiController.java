package com.liust.server.controller;

import com.liust.server.controller.message.AbstractController;
import com.liust.server.controller.message.MessageBean;
import com.liust.server.enums.DownloadEnum;
import com.liust.server.model.DBConfigModel;
import com.liust.server.model.DBModel;
import com.liust.server.model.DBVOModel;
import com.liust.server.model.TableFileInfoModel;
import com.liust.server.model.TableModel;
import com.liust.server.service.DBServer;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import java.io.BufferedOutputStream;
import java.nio.charset.StandardCharsets;
import java.util.List;
import java.util.zip.ZipOutputStream;

import javax.servlet.http.HttpServletResponse;

/**
 * @program: springdemo
 * @description:
 * @author: liust
 * @create: 2019-04-04 18:17
 **/
@Controller
public class ApiController extends AbstractController {

    private final DBServer dbServer;

    @Autowired
    public ApiController(DBServer dbServer) {
        this.dbServer = dbServer;
    }

    @PostMapping("show/database")
    @ResponseBody
    public MessageBean<List<DBModel>> showDatabase(@RequestBody DBConfigModel dbConfigModel) {
        return this.process(dbServer.getDBs(dbConfigModel));
    }

    @PostMapping("show/tables")
    @ResponseBody
    public MessageBean<List<TableModel>> showTables(@RequestBody DBConfigModel dbConfigModel) {
        return this.process(dbServer.getDBTables(dbConfigModel));
    }

    @PostMapping("show/domain")
    @ResponseBody
    public MessageBean<TableFileInfoModel> show(@RequestBody DBVOModel dbvoModel) {
        dbvoModel.setAttributes(DownloadEnum.SINGLETABLE.getAttributes());
        return this.process(dbServer.getTableInfo(dbvoModel).get(0));
    }

    /**
     * 单表下载
     */
    @RequestMapping(value = "download/table",method = RequestMethod.POST)
    public void singleTable(HttpServletResponse response,@RequestBody DBVOModel dbvoModel) {
        response.setContentType("application/octet-stream");
        String fileName = new String((dbvoModel.getDbConfigModel().getDbName() + ".zip").getBytes(StandardCharsets.UTF_8), StandardCharsets.ISO_8859_1);
        try (ZipOutputStream zos = new ZipOutputStream(new BufferedOutputStream(response.getOutputStream()))) {
            response.setHeader("Content-disposition", "attachment; filename=" + fileName);
            dbServer.tablesToZip(dbvoModel, zos);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}