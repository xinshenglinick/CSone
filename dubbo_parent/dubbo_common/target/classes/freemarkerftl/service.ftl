package com.xsl.service;

import com.xsl.pojo.${entityName};
import entity.PageResult;

import java.util.List;

/**
 * @描述
 * @创建人 wangyue
 * @创建时间2020/12/811:00
 */

public interface ${entityName}Service {

    /**
     * 返回全部列表
     * @return
     */
    public List<${entityName}> findAll();

    PageResult<${entityName}> pageList(${entityName} ${entityNameLower}, int pageSize, int pageNum);

    void save(${entityName} ${entityNameLower});

    void deletebth(${idfield[1]}[] ids);

    ${entityName} getById(${idfield[1]} id);
}
