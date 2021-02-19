package com.xsl.shop.controller;


import com.alibaba.dubbo.config.annotation.Reference;
import com.xsl.entity.PageResult;
import com.xsl.entity.Result;
import com.xsl.pojo.Shop;
import com.xsl.service.ShopService;
import org.springframework.jms.core.MessageCreator;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import javax.jms.JMSException;
import javax.jms.MapMessage;
import javax.jms.Message;
import javax.jms.Session;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

@RestController
@RequestMapping("/shop")
public class ShopController {
    @Reference
    private ShopService shopService;


    @RequestMapping("findAll")
    public List<Shop> findAll() {
        return shopService.findAll();
    }

    /**
     *
     * 模糊分页
     * @param shop
     * @param pageSize
     * @param pageNum
     * @return
     */
    @RequestMapping("pageList")
    public PageResult<Shop> pageList(@RequestBody(required = false) Shop shop,
                                     @RequestParam(defaultValue = "3") Integer pageSize,
                                     @RequestParam(defaultValue = "1") Integer pageNum){

        return shopService.pageList(shop,pageSize,pageNum);
    }

    /**
     * 新增修改-----注册
     * @param shop
     * @return
     */
    @RequestMapping("save")
    public Result save(@RequestBody Shop shop){
        try {
            shopService.saveShop(shop);
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
    public Result deletebth(@RequestBody Long[] ids){
        try {
            shopService.deleteShop(ids);
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
    public Shop getById(Long id){
        return shopService.getById(id);
    }
}
