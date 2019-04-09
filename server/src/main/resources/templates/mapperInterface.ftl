package ${packageName}.mapper;

import ${packageName}.domain.${tableModel.className};

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 *${tableModel.className} Mapper接口
 */
@Mapper
public interface ${tableModel.className}Mapper{

    int insert(@Param("domain") ${tableModel.className} domain);

    int insertList(@Param("domains") List<${tableModel.className}> domains);

    int update(@Param("domain") ${tableModel.className} domain);

    List<${tableModel.className}> select(@Param("domain") ${tableModel.className} domain);

    ${tableModel.className} selectOne(@Param("domain") ${tableModel.className} domain);

    List<${tableModel.className}> selectAll();
}