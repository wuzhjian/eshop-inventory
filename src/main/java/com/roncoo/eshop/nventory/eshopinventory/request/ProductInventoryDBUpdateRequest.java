package com.roncoo.eshop.nventory.eshopinventory.request;

import com.roncoo.eshop.nventory.eshopinventory.model.ProductInventory;
import com.roncoo.eshop.nventory.eshopinventory.service.ProductInventoryService;

/**
 * 发生交易，就要修改库存
 * 此时就会发送请求过来，要求修改库存，可能就是所谓的data update request ，数据更新请求
 *
 * cache aside pattern
 * 1. 删除缓存
 * 2. 更新数据库
 * @author 44644
 */
public class ProductInventoryDBUpdateRequest implements Request {

    /**
     * 商品库存
     */
    private ProductInventory productInventory;

    /**
     * 商品库存service
     */
    private ProductInventoryService productInventoryService;


    public ProductInventoryDBUpdateRequest(ProductInventory productInventory, ProductInventoryService productInventoryService) {
        this.productInventory = productInventory;
        this.productInventoryService = productInventoryService;
    }

    @Override
    public void process() {
        // 删除redis中缓存
        productInventoryService.removeProductInventoryCache(productInventory);
        // 修改数据库
        productInventoryService.updateProductInventory(productInventory);
    }

    /**
     * 获取一个商品id
     * @return
     */
    @Override
    public Integer getProduceId() {
        return productInventory.getProductId();
    }
}
