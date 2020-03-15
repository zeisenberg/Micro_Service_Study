package com.szkingdom.order.server.repository;

import com.szkingdom.order.server.OrderApplicationTests;
import com.szkingdom.order.server.dataobject.OrderMaster;
import com.szkingdom.order.server.enums.OrderStatusEnum;
import com.szkingdom.order.server.enums.PayStatusEnum;
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
public class OrderMasterRepositoryTest extends OrderApplicationTests {

    @Autowired
    private OrderMasterRepository orderMasterRepository;

    @Test
    public void testSave() {
        OrderMaster orderMaster = new OrderMaster();
        orderMaster.setOrderId("201912220001");
        orderMaster.setBuyerName("五帝座一");
        orderMaster.setBuyerPhone("17777777777");
        orderMaster.setBuyerAddress("西青区开华道100号");
        orderMaster.setBuyerOpenid("1101110");
        orderMaster.setOrderAmount(new BigDecimal(3.9));
        orderMaster.setOrderStatus(OrderStatusEnum.NEW.getCode());
        orderMaster.setPayStatus(PayStatusEnum.WAIT.getCode());

        OrderMaster result = orderMasterRepository.save(orderMaster);
        Assert.assertTrue(result != null);
    }

}