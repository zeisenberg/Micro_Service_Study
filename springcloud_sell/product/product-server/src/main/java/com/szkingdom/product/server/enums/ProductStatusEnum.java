package com.szkingdom.product.server.enums;

import lombok.Getter;

/**
 * @Description 商品上下架状态
 * @Author zhaizhengwei
 */
@Getter
public enum ProductStatusEnum {
    UP(0, "在架"),
    DOWN(1, "下架"),;

    private Integer code;

    private String message;

    ProductStatusEnum(Integer code, String message) {
        this.code = code;
        this.message = message;
    }
}
