package com.roncoo.eshop.nventory.eshopinventory.service;

import com.roncoo.eshop.nventory.eshopinventory.model.ProductInventory;

/**
 * 商品库存接口
 */
public interface ProductInventoryService {
    /**
     * 更新商品库存
     * @param productInventory
     */
    void updateProductInventory(ProductInventory productInventory);

    /**
     * 删除Redis中的商品库存的缓存
     * @param productInventory
     */
    void removeProductInventoryCache(ProductInventory productInventory);

    /**
     * 根据商品id去查询商品库存
     * @param productId
     * @return
     */
    ProductInventory findProductInventory(Integer productId);

    /**
     * 设置商品库存缓存
     * @param productInventoryCache
     */
    void setProductInventoryCache(ProductInventory productInventoryCache);
}
