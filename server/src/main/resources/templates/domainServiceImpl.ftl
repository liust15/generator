package ${packageName}.service.data;

import ${packageName}.domain.${tableModel.className};
import ${packageName}.mapper.${tableModel.className}Mapper;
import ${packageName}.service.data.impl.${tableModel.className}Service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import java.util.Collections;
import java.util.List;


/**
 *${tableModel.className} Server实现
 */
@Service
public class ${tableModel.className}ServiceImpl implements ${tableModel.className}Service{

    @Autowired
    private ${tableModel.className}Mapper mapper;
    /**
    * 单条插入
    */
    @Override
    public int insert(${tableModel.className} domain) {
    return mapper.insert(domain);
    }

    /**
    * 多条插入
    */
    @Override
    public int insertList(List<${tableModel.className}> domains) {
        if (CollectionUtils.isEmpty(domains))return 0;
        return mapper.insertList(domains);
    }

    /**
    * 更新
    */
    @Override
    public int update(${tableModel.className} domain) {
        return mapper.update(domain);
    }

    /**
    * 查询
    */
    @Override
    public List<${tableModel.className}> select(${tableModel.className} domain) {
        List<${tableModel.className}> list = mapper.select(domain);
        if (CollectionUtils.isEmpty(list))
            return Collections.emptyList();
        return list;
    }

    /**
    * 单条查询
    */
    @Override
    public ${tableModel.className} selectOne(${tableModel.className} domain) {
        return mapper.selectOne(domain);
    }
}

