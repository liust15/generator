package com.liust.server.enums;

public enum TemplatesEnum {
    DOMAIN("domain.ftl",".java","/domain/"),
    DOMAINSERVERIMPL("domainServiceImpl.ftl","ServiceImpl.java","/service/data/impl/"),
    DOMAINSERVER("domainService.ftl","Service.java","/service/data/"),
    MAPPERINTERFACE("mapperInterface.ftl","Mapper.java","/mapper/"),
    MAPPERXML("mapperXml.ftl","Mapper.xml","resources/mybatis/mapper");

    private final String templateName;
    private final String fileSuffix;
    private String fliePath;

    public String getTemplateName() {
        return templateName;
    }

    public String getFileSuffix() {
        return fileSuffix;
    }

    public String getFliePath() {
        return fliePath;
    }

    TemplatesEnum(String templateName, String fileSuffix, String fliePath) {
        this.templateName = templateName;
        this.fileSuffix = fileSuffix;
        this.fliePath = fliePath;
    }
}
