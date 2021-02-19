package com.xsl.shop.controller;

import com.alibaba.dubbo.config.annotation.Reference;
import com.xsl.pojo.${entityName};
import com.xsl.service.${entityName}Service;
import entity.PageResult;
import entity.Result;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * @描述
 * @创建人 wangyue
 * @创建时间2020/12/811:02
 */
@RequestMapping("${entityNameLower}")
@RestController
public class ${entityName}Controller {

    @Reference
    private ${entityName}Service ${entityNameLower}Service;

    @RequestMapping("findAll")
    public List<${entityName}> findAll() {
        return ${entityNameLower}Service.findAll();
    }

    /**
     *
     * 模糊分页
     * @param ${entityNameLower}
     * @param pageSize
     * @param pageNum
     * @return
     */
    @RequestMapping("pageList")
    public PageResult<${entityName}> pageList(@RequestBody(required = false) ${entityName} ${entityNameLower},
                                        @RequestParam(defaultValue = "3") int pageSize,
                                        @RequestParam(defaultValue = "1") int pageNum){

        return ${entityNameLower}Service.pageList(${entityNameLower},pageSize,pageNum);
    }

    /**
     * 新增修改
     * @param ${entityNameLower}
     * @return
     */
    @RequestMapping("save")
    public Result save(@RequestBody ${entityName} ${entityNameLower}){
        try {
            ${entityNameLower}Service.save(${entityNameLower});
            return  new Result(true,"操作成功");
        } catch (Exception e) {
            e.printStackTrace();
            return  new Result(false,"操作失败");
        }
    }

    /**
     * 批量删除
     * @param ids
     * @return
     */
    @RequestMapping("deletebth")
    public Result deletebth(${idfield[1]}[] ids){
        try {
            ${entityNameLower}Service.deletebth(ids);
            return  new Result(true,"操作成功");
        } catch (Exception e) {
            e.printStackTrace();
            return  new Result(false,"操作失败");
        }
    }

    /**
     * 根据主键查询
     * @param id
     * @return
     */
    @RequestMapping("getById")
    public ${entityName} getById(${idfield[1]} id){
        return ${entityNameLower}Service.getById(id);
    }

}
