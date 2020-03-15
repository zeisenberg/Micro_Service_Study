package com.szkingdom.order.server.message;

import com.fasterxml.jackson.core.type.TypeReference;
import com.szkingdom.order.server.utils.JsonUtil;
import com.szkingdom.product.common.ProductInfoOutput;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.rabbit.annotation.Queue;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * @Description
 * @Author zhaizhengwei
 */
@Component
@Slf4j
public class ProductInfoReceiver {

    private static final String PRODUCT_STOCK_TEMPLATE = "product_stock_%s";

    @Autowired
    private StringRedisTemplate stringRedisTemplate;

    @RabbitListener(queuesToDeclare = @Queue("productInfo"))
    public void process(String message) {
        //message => productInfoOutput
        List<ProductInfoOutput> productInfoOutputs = (List<ProductInfoOutput>) JsonUtil.fromJsonToList2(message, ProductInfoOutput.class);
        log.info("从队列【{}】接收到消息：{}", "productInfo", productInfoOutputs);
        //把product_id存到redis缓存
        for(ProductInfoOutput productInfoOutput : productInfoOutputs) {
            stringRedisTemplate.opsForValue().set(String.format(PRODUCT_STOCK_TEMPLATE, productInfoOutput.getProductId()),
                    productInfoOutput.getProductStock().toString());
        }

    }
}
