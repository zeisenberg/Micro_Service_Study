package com.szkingdom.order.server.service.impl;

import com.szkingdom.order.server.dataobject.OrderDetail;
import com.szkingdom.order.server.dataobject.OrderMaster;
import com.szkingdom.order.server.dto.OrderDTO;
import com.szkingdom.order.server.enums.OrderStatusEnum;
import com.szkingdom.order.server.enums.PayStatusEnum;
import com.szkingdom.order.server.enums.ResultEnum;
import com.szkingdom.order.server.exception.OrderException;
import com.szkingdom.order.server.repository.OrderDetailRepository;
import com.szkingdom.order.server.repository.OrderMasterRepository;
import com.szkingdom.order.server.service.OrderService;
import com.szkingdom.order.server.utils.KeyUtil;
import com.szkingdom.product.client.ProductClient;
import com.szkingdom.product.common.DecreaseStockInput;
import com.szkingdom.product.common.ProductInfoOutput;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.CollectionUtils;

import java.math.BigDecimal;
import java.math.BigInteger;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

/**
 * @Description
 * @Author zhaizhengwei
 */
@Service
public class OrderServiceImpl implements OrderService {

    @Autowired
    private OrderDetailRepository orderDetailRepository;

    @Autowired
    private OrderMasterRepository orderMasterRepository;

    @Autowired
    private ProductClient productClient;

    @Override
    @Transactional
    public OrderDTO create(OrderDTO orderDTO) {
        String orderId = KeyUtil.genUniqueKey();

       //查询商品信息(调用商品服务)
        List<String> productIdList = orderDTO.getOrderDetailList().stream()
                .map(OrderDetail::getProductId)
                .collect(Collectors.toList());
        List<ProductInfoOutput> productInfoList = productClient.listForOrder(productIdList);

       //计算总价
        BigDecimal orderAmout = new BigDecimal(BigInteger.ZERO);
        for (OrderDetail orderDetail: orderDTO.getOrderDetailList()) {
            for (ProductInfoOutput productInfo: productInfoList) {
                if (productInfo.getProductId().equals(orderDetail.getProductId())) {
                    //单价*数量
                    orderAmout = productInfo.getProductPrice()
                            .multiply(new BigDecimal(orderDetail.getProductQuantity()))
                            .add(orderAmout);
                    BeanUtils.copyProperties(productInfo, orderDetail);
                    orderDetail.setOrderId(orderId);
                    orderDetail.setDetailId(KeyUtil.genUniqueKey());
                    //订单详情入库
                    orderDetailRepository.save(orderDetail);
                }
            }
        }

       //扣库存(调用商品服务)
        List<DecreaseStockInput> decreaseStockInputList = orderDTO.getOrderDetailList().stream()
                .map(e -> new DecreaseStockInput(e.getProductId(), e.getProductQuantity()))
                .collect(Collectors.toList());
        productClient.decreaseStock(decreaseStockInputList);

       //订单入库
        OrderMaster orderMaster = new OrderMaster();
        orderDTO.setOrderId(KeyUtil.genUniqueKey());
        BeanUtils.copyProperties(orderDTO, orderMaster);
        orderMaster.setOrderAmount(orderAmout);
        orderMaster.setOrderStatus(OrderStatusEnum.NEW.getCode());
        orderMaster.setPayStatus(PayStatusEnum.WAIT.getCode());
        orderMasterRepository.save(orderMaster);
        return orderDTO;
    }

    @Override
    @Transactional
    public OrderDTO finish(String orderId) {
        // 1.查询订单
        Optional<OrderMaster> orderMasterOptional =  orderMasterRepository.findById(orderId);
        if(!orderMasterOptional.isPresent()) {
            throw new OrderException(ResultEnum.ORDER_NOT_EXIST);
        }

        // 2.判断订单状态
        OrderMaster orderMaster = orderMasterOptional.get();
        if(OrderStatusEnum.NEW.getCode() != orderMaster.getOrderStatus()) {
            throw new OrderException(ResultEnum.ORDER_STATUS_ERROR);
        }

        // 3.修改订单状态为完结
        orderMaster.setOrderStatus(OrderStatusEnum.FINISHED.getCode());
        orderMasterRepository.save(orderMaster);

        // 4.查询订单详情
        List<OrderDetail> orderDetails = orderDetailRepository.findByOrderId(orderId);
        if (CollectionUtils.isEmpty(orderDetails)) {
            throw new OrderException(ResultEnum.ORDER_DETAIL_NOT_EXIST);
        }
        OrderDTO orderDTO = new OrderDTO();
        BeanUtils.copyProperties(orderMaster, orderDTO);
        orderDTO.setOrderDetailList(orderDetails);

        return orderDTO;
    }
}
