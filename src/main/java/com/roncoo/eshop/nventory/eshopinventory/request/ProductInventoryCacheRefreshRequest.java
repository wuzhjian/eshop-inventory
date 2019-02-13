package com.roncoo.eshop.nventory.eshopinventory.request;

import com.roncoo.eshop.nventory.eshopinventory.model.ProductInventory;
import com.roncoo.eshop.nventory.eshopinventory.service.ProductInventoryService;

/**
 * 重新加载商品库存的缓存
 */
public class ProductInventoryCacheRefreshRequest implements Request {

    private Integer productId;

    private ProductInventoryService productInventoryService;


    public ProductInventoryCacheRefreshRequest(Integer productId, ProductInventoryService productInventoryService) {
        this.productId = productId;
        this.productInventoryService = productInventoryService;
    }


    @Override
    public void process() {
        // 从数据库中查询最新的商品库存数量
        ProductInventory productInventory = productInventoryService.findProductInventory(productId);
        // 将最新的商品库存数量，刷新至缓存中去
        productInventoryService.setProductInventoryCache(productInventory);
    }

    @Override
    public Integer getProduceId() {
        return productId;
    }
}
