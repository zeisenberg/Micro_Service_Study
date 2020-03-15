package com.szkingdom.order.server.VO;

import lombok.Data;

/**
 * @Description
 * @Author zhaizhengwei
 */
@Data
public class ResultVO<T> {

    private Integer code;

    private String msg;

    private T data;
}
