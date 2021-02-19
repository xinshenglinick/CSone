package com.xsl.service;

import com.xsl.entity.PageResult;
import com.xsl.pojo.Shop;

import java.util.List;

public interface ShopService {

    public List<Shop> findAll();

    PageResult<Shop> pageList(Shop shop,Integer pageSize,Integer pageNum);

    void saveShop(Shop shop);

    void deleteShop(Long[] ids);

    Shop getById(Long id);

}
