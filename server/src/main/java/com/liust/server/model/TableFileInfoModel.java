package com.liust.server.model;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

/**
 * 一张表的全部字段
 */
@Builder
@Setter
@Getter
public class TableFileInfoModel {
    private String tableName;
    //表对应数据
    private String domain;
    //mybaits 对应的接口
    private String mapperInter;
    //service
    private String service;
    private String serviceImpl;
    //mybaits 对应的xml
    private String mapperXml;
}
