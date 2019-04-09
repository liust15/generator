package com.liust.server.enums;

import lombok.Getter;

@Getter
public enum DownloadEnum {
    SINGLETABLE("单表下载", 0), FULLTABLE("全表下载", 1);
    private String name;
    private Integer attributes;

    DownloadEnum(String name, Integer attributes) {
        this.name = name;
        this.attributes = attributes;
    }
}
