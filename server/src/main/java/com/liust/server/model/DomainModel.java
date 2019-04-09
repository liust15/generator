package com.liust.server.model;

import java.util.List;

import lombok.Data;

/**
 * @program: springdemo
 * @description:
 * @author: liust
 * @create: 2019-04-04 21:10
 **/
@Data
public class DomainModel {
    private String packageName;
    private List<TableInfo> tableInfos;
    private TableModel tableModel;
}
