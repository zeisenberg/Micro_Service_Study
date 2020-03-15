package com.szkingdom.product.server.enums;

import lombok.Getter;

/**
 * @Description 服务响应状态
 * @Author zhaizhengwei
 */
@Getter
public enum ResultEnum {

    PRODUCT_NOT_EXIST(1, "商品不存在"),
    PRODUCT_STOCK_ERROR(2, "库存有误"),;

    private Integer code;

    private String message;

    ResultEnum(Integer code, String message) {
        this.code = code;
        this.message = message;
    }
}
