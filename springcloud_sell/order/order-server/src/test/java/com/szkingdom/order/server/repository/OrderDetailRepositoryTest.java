package com.szkingdom.order.server.repository;

import com.szkingdom.order.server.OrderApplicationTests;
import com.szkingdom.order.server.dataobject.OrderDetail;
import org.junit.Assert;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;

/**
 * @Description
 * @Author zhaizhengwei
 */
@Component
public class OrderDetailRepositoryTest extends OrderApplicationTests {

    @Autowired
    private OrderDetailRepository orderDetailRepository;

    @Test
    public void testSave() {
        OrderDetail orderDetail = new OrderDetail();
        orderDetail.setDetailId("201912221002");
        orderDetail.setOrderId("201912220002");
        orderDetail.setProductIcon("http://xxx.com");
        orderDetail.setProductId("157875196366160022");
        orderDetail.setProductName("宜宾燃面");
        orderDetail.setProductPrice(new BigDecimal(0.01));
        orderDetail.setProductQuantity(2);

        OrderDetail result = orderDetailRepository.save(orderDetail);
        Assert.assertTrue(result != null);
    }

}