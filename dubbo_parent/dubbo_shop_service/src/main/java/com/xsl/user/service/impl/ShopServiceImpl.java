package com.xsl.user.service.impl;

import com.alibaba.dubbo.config.annotation.Service;
import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.xsl.entity.PageResult;
import com.xsl.mapper.ShopMapper;
import com.xsl.pojo.Shop;
import com.xsl.pojo.ShopExample;
import com.xsl.service.ShopService;

import javax.annotation.Resource;
import java.util.List;

@Service(timeout = 5000,retries = 0)
public class ShopServiceImpl implements ShopService {

    @Resource
    private ShopMapper shopMapper;

    @Override
    public List<Shop> findAll() {
        List<Shop> shops = shopMapper.selectByExample(null);
        return shops;
    }

    @Override
    public PageResult<Shop> pageList(Shop shop, Integer pageSize, Integer pageNum) {

        PageHelper.startPage(pageNum,pageSize);

        ShopExample example=new ShopExample();
        ShopExample.Criteria criteria = example.createCriteria();
        if(shop!=null){

        }
        Page<Shop> page = (Page<Shop>) shopMapper.selectByExample(example);
        return new PageResult<>(page.getTotal(),page.getResult());
    }

    @Override
    public void saveShop(Shop shop) {
        Integer id = shop.getId();
        if(id==null||id==0){
            shopMapper.insert(shop);
        }else{
            shopMapper.updateByPrimaryKey(shop);
        }
    }

    @Override
    public void deleteShop(Long[] ids) {
        for (Long id : ids) {
            shopMapper.deleteByPrimaryKey(id);
        }
    }

    @Override
    public Shop getById(Long id) {
        return shopMapper.selectByPrimaryKey(id);
    }
}
