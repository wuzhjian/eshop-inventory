package com.roncoo.eshop.nventory.eshopinventory.mapper;

import com.roncoo.eshop.nventory.eshopinventory.model.ProductInventory;
import org.apache.ibatis.annotations.Param;

/**
 * 库存数量Mapper
 * @author 44644
 */
public interface ProductInventoryMapper {
    /**
     * 更新商品库存
     * @param inventoryCnt
     */
    void updateProduceInventory(ProductInventory inventoryCnt);

    /**
     * 根据商品id查询商品库存信息
     * @param productId
     * @return
     */
    ProductInventory findProductInventory(@Param("productId") Integer productId);
}
