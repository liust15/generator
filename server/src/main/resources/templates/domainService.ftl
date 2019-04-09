package ${packageName}.service.data.impl;

import ${packageName}.domain.${tableModel.className};

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import java.util.List;
import java.util.Map;

/**
 *${tableModel.className} Server接口
 */
@Mapper
public interface ${tableModel.className}Service{
    /**
    * 单条插入
    */
    int insert(${tableModel.className} domain);

    /**
    * 多条插入
    */
    int insertList(List<${tableModel.className}> domains);

    /**
    * 更新
    */
    int update(${tableModel.className} domain);

    /**
    * 查询
    */
    List<${tableModel.className}> select(${tableModel.className} domain);

    /**
    * 单条查询
    */
    ${tableModel.className} selectOne(${tableModel.className} domain);

<#--    /*
    * 查询全部数据
    *
    */
    List<${tableModel.className}> selectAll();-->
}
