package com.szkingdom.order.server.service;


import com.szkingdom.order.server.dto.OrderDTO;

/**
 * @Description
 * @Author zhaizhengwei
 */
public interface OrderService {

    /**
     * 创建订单
     * @param orderDTO
     * @return
     */
    OrderDTO create(OrderDTO orderDTO);

    /**
     * 完结订单
     * @param orderId
     * @return
     */
    OrderDTO finish(String orderId);
}
