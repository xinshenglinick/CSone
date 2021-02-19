package com.xsl.sellergoods.service.impl;

import com.alibaba.dubbo.config.annotation.Service;
import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.mysql.jdbc.StringUtils;
import com.xsl.mapper.${entityName}Mapper;
import com.xsl.pojo.${entityName};
import com.xsl.pojo.${entityName}Example;
import com.xsl.service.${entityName}Service;
import entity.PageResult;

import javax.annotation.Resource;
import java.util.List;

/**
 * @描述
 * @创建人 wangyue
 * @创建时间2020/12/811:01
 */
@Service(timeout = 5000,retries = 0)
public class ${entityName}ServiceImpl implements ${entityName}Service {

    @Resource
    private ${entityName}Mapper ${entityNameLower}Mapper;

    @Override
    public List<${entityName}> findAll() {
        return ${entityNameLower}Mapper.selectByExample(null);
    }

    @Override
    public PageResult<${entityName}> pageList(${entityName} ${entityNameLower}, int pageSize, int pageNum) {
        PageHelper.startPage(pageNum,pageSize);
        ${entityName}Example example = new ${entityName}Example();
        ${entityName}Example.Criteria criteria = example.createCriteria();
        if(${entityNameLower} !=null){

        }

        Page<${entityName}> page = (Page<${entityName}>) ${entityNameLower}Mapper.selectByExample(example);

        return new PageResult<>(page.getTotal(),page.getResult());
    }

    @Override
    public void save(${entityName} ${entityNameLower}) {
        ${idfield[1]} id =${entityNameLower}.get${idfield[4]}();
        <#if idfield[1] == "String">
        if(id == null || id.equals("")){
        <#else>
        if(id == null || id == 0){
        </#if>
            ${entityNameLower}Mapper.insert(${entityNameLower});
        } else{
            ${entityNameLower}Mapper.updateByPrimaryKey(${entityNameLower});
        }
    }

    @Override
    public void deletebth(${idfield[1]}[] ids) {
        for (${idfield[1]} id : ids) {
            ${entityNameLower}Mapper.deleteByPrimaryKey(id);
        }
    }

    @Override
    public ${entityName} getById(${idfield[1]} id) {
        return ${entityNameLower}Mapper.selectByPrimaryKey(id);
    }
}
