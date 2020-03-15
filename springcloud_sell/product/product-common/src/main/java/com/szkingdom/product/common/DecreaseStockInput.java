package com.szkingdom.product.common;

import lombok.Data;

/**
 * @Description 减库存入参
 * Created by zhaizhengwei
 */
@Data
public class DecreaseStockInput {

    private String productId;

    private Integer productQuantity;

    public DecreaseStockInput() {
    }

    public DecreaseStockInput(String productId, Integer productQuantity) {
        this.productId = productId;
        this.productQuantity = productQuantity;
    }
}