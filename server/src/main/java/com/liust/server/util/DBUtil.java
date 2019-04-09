package com.liust.server.util;

import lombok.extern.slf4j.Slf4j;

/**
 * @program: springdemo
 * @description:
 * @author: liust
 * @create: 2019-04-04 21:44
 **/
@Slf4j
public class DBUtil {
    /**
     * 下划线转驼峰
     */
    public static String UnderlineToHump(String para) {
        StringBuilder result = new StringBuilder();
        String a[] = para.split("_");
        for (String s : a) {
            if (!para.contains("_")) {
                result.append(s);
                continue;
            }
            if (result.length() == 0) {
                result.append(s.toLowerCase());
            } else {
                result.append(s.substring(0, 1).toUpperCase());
                result.append(s.substring(1).toLowerCase());
            }
        }
        return result.toString();
    }

    /**
     * 数据类型转化JAVA
     *
     * @param sqlType：类型名称
     * @return
     */
    public static String toSqlToJava(String sqlType) {
        if (sqlType == null || sqlType.trim().length() == 0)
            throw new RuntimeException("-----------------》转化失败：类型不存在" + sqlType);
        sqlType = sqlType.toLowerCase();
        switch (sqlType) {
            case "nvarchar":
                return "String";
            case "char":
                return "String";
            case "varchar":
                return "String";
            case "text":
                return "String";
            case "nchar":
                return "String";
            case "blob":
                return "byte[]";
            case "integer":
                return "Long";
            case "tinyint":
                return "Integer";
            case "int":
                return "Integer";
            case "smallint":
                return "Integer";
            case "mediumint":
                return "Integer";
            case "bit":
                return "Boolean";
            case "bigint":
                return "java.math.BigInteger";
            case "float":
                return "Fload";
            case "double":
                return "Double";
            case "decimal":
                return "java.math.BigDecimal";
            case "boolean":
                return "Boolean";
            case "id":
                return "Long";
            case "date":
                return "java.util.Date";
            case "datetime":
                return "java.util.Date";
            case "year":
                return "java.util.Date";
            case "time":
                return "java.sql.Time";
            case "timestamp":
                return "java.sql.Timestamp";
            case "numeric":
                return "java.math.BigDecimal";
            case "real":
                return "java.math.BigDecimal";
            case "money":
                return "Double";
            case "smallmoney":
                return "Double";
            case "image":
                return "byte[]";
            case "enum":
                return "enum";
            case "longtext":
                return "String";
            default:
                throw new RuntimeException("-----------------》转化失败：未发现的类型" + sqlType);
        }
    }
    /**
     * 首字母大写
     *
     * @param  str
     * @return String
     */
    public static String upperCase(String str) {
        char[] ch = str.toCharArray();
        if (ch[0] >= 'a' && ch[0] <= 'z') {
            ch[0] = (char) (ch[0] - 32);
        }
        return new String(ch);
    }
}
