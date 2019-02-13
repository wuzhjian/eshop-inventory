package com.roncoo.eshop.nventory.eshopinventory.service.impl;

import com.roncoo.eshop.nventory.eshopinventory.dao.RedisDAO;
import com.roncoo.eshop.nventory.eshopinventory.mapper.ProductInventoryMapper;
import com.roncoo.eshop.nventory.eshopinventory.model.ProductInventory;
import com.roncoo.eshop.nventory.eshopinventory.service.ProductInventoryService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

/**
 * 商品库存实现类
 */
@Service("productInventoryService")
public class ProductInventoryServiceImpl implements ProductInventoryService {

    @Resource
    private ProductInventoryMapper productInventoryMapper;

    @Resource
    private RedisDAO redisDAO;

    @Override
    public void updateProductInventory(ProductInventory productInventory) {
        productInventoryMapper.updateProduceInventory(productInventory);
    }

    @Override
    public void removeProductInventoryCache(ProductInventory productInventory) {
        String key = "product:inventory:" + productInventory.getProductId();
        redisDAO.delete(key);
    }

    @Override
    public ProductInventory findProductInventory(Integer productId) {
        return productInventoryMapper.findProductInventory(productId);
    }

    @Override
    public void setProductInventoryCache(ProductInventory productInventory) {
        String key = "product:inventory:" + productInventory.getProductId();
        redisDAO.set(key, String.valueOf(productInventory.getInventoryCnt()));

    }


}
