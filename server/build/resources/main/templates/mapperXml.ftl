<?xml version="1.0" encoding="UTF-8"?>
<!DOCTYPE mapper PUBLIC "-//mybatis.org//DTD Mapper 3.0//EN"
        "http://mybatis.org/dtd/mybatis-3-mapper.dtd">
<mapper namespace="${packageName}.mapper.${tableModel.className}Mapper">

    <!-- 通用查询映射结果 -->
    <resultMap id="baseResultMap" type="${packageName}.domain.${tableModel.className}">
<#--        <id column="FuiId" property="id"/> -->
        <#list tableInfos as tableInfo>
            <!-- ${tableInfo.comment} -->
            <result column="${tableInfo.field}" property="${tableInfo.field}"/>
        </#list>
    </resultMap>

    <!-- 通用查询结果列 -->
    <sql id="baseColumnList">
        <#list tableInfos as tableInfo>
            `${tableInfo.field}`
        </#list>
    </sql>
    
    <insert id="insert">
        INSERT INTO ${tableModel.tableName}
        <trim prefix="(" suffix=")" suffixOverrides=",">
            <#list tableInfos as tableInfo>
            <if test="domain.${tableInfo.field} != null">`${tableInfo.field}`,</if>
            </#list>
        </trim>
        VALUES
        <trim prefix="(" suffix=")" suffixOverrides=",">
            <#list tableInfos as tableInfo>
            <if test="domain.${tableInfo.field} != null">${r'#{domain.'}${tableInfo.field}},</if>
            </#list>
        </trim>
    </insert>

    <insert id="insertList">
        INSERT INTO ${tableModel.tableName}(
        <include refid="baseColumnList"/>
        )VALUES
        <foreach collection="domains" item="domain" index="index" separator=",">
            (
            <#list tableInfos as tableInfo>
                ${r'#{domain.'}${tableInfo.field}},
            </#list>
            )
        </foreach>
    </insert>

    <update id="update">
        UPDATE ${tableModel.tableName}
        <set>
            <#list tableInfos as tableInfo>
            <if test="domain.${tableInfo.field} != null">`${tableInfo.field}`=${r'#{domain.'}${tableInfo.field}},</if>
            </#list>
        </set>
        WHERE id = ${r'#{domain.'}id}
    </update>

    <select id="select" resultMap="baseResultMap">
        SELECT
        <include refid="baseColumnList"/>
        FROM ${tableModel.tableName}
        <if test="domain != null">
            <where>
            <#list tableInfos as tableInfo>
                <if test="domain.${tableInfo.field} != null">AND `${tableInfo.field}`=${r'#{domain.'}${tableInfo.field}},</if>
            </#list>
            </where>
        </if>
    </select>

    <select id="selectOne" resultMap="baseResultMap">
        SELECT
        <include refid="baseColumnList"/>
        FROM ${tableModel.tableName}
        <if test="domain != null">
            <where>
            <#list tableInfos as tableInfo>
                <if test="domain.${tableInfo.field} != null">AND `${tableInfo.field}`=${r'#{domain.'}${tableInfo.field}},</if>
            </#list>
            </where>
        </if>
        LIMIT 1
    </select>

    <select id="selectAll" resultMap="baseResultMap">
        SELECT
        <include refid="baseColumnList"/>
        FROM ${tableModel.tableName}
    </select>
</mapper>