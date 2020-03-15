package com.szkingdom.order.server.exception;


import com.szkingdom.order.server.enums.ResultEnum;

/**
 * @Description
 * @Author zhaizhengwei
 */
public class OrderException extends RuntimeException {

    private Integer code;

    public OrderException(Integer code, String message) {
        super(message);
        this.code = code;
    }

    public OrderException(ResultEnum resultEnum) {
        super(resultEnum.getMessage());
        this.code = resultEnum.getCode();
    }
}
