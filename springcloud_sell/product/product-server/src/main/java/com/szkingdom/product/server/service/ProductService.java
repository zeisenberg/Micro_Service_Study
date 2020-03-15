package com.szkingdom.product.server.service;

import com.szkingdom.product.common.DecreaseStockInput;
import com.szkingdom.product.common.ProductInfoOutput;
import com.szkingdom.product.server.dataobject.ProductInfo;

import java.util.List;

/**
 * @Description 商品服务
 * @Author zhaizhengwei
 */
public interface ProductService {

    /**
     * 查询所有在架商品列表
     */
    List<ProductInfo> findUpAll();

    /**
     * 查询商品列表
     *
     * @param productIdList
     * @return
     */
    List<ProductInfoOutput> findList(List<String> productIdList);

    /**
     * 扣库存
     *
     * @param decreaseStockInputList
     */
    void decreaseStock(List<DecreaseStockInput> decreaseStockInputList);

}
