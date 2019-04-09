package ${packageName}.domain;

import java.io.Serializable;

import lombok.Data;
import lombok.NoArgsConstructor;

/**
 *<#if tableModel.tableComment??>${tableModel.tableComment}</#if>
 */
@Data
@NoArgsConstructor
public class ${tableModel.className} implements Serializable {

<#list tableInfos as tableInfo>

    private ${tableInfo.type} ${tableInfo.field};
    /**
    * ${tableInfo.comment}
    */
</#list>

}