package com.liust.server.model;

import lombok.Data;

/**
 * @program: springdemo
 * @description: 表字段信息
 * @author: liust
 * @create: 2019-04-04 20:44
 **/
@Data
public class TableInfo {
    private String field;
    private String key;
    private String type;
    private String comment;
}
