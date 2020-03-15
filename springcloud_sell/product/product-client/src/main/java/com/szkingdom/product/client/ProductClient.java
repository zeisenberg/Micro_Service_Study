package com.szkingdom.product.client;

import com.szkingdom.product.common.DecreaseStockInput;
import com.szkingdom.product.common.ProductInfoOutput;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Controller;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.List;

/**
 * @Description
 * @Author zhaizhengwei
 */
@FeignClient(name = "product", fallback = ProductClient.ProductClientFallback.class)
public interface ProductClient {

    @GetMapping("/msg")
    String productMsg();

    /**
     * 获取商品列表(给订单服务用的)
     * @param productIdList
     * @return
     */
    @PostMapping("/product/listForOrder")
    public List<ProductInfoOutput> listForOrder(@RequestBody List<String> productIdList);

    @PostMapping("product/decreaseStock")
    public void decreaseStock(@RequestBody List<DecreaseStockInput> decreaseStockInputList);

    @Component
    static class ProductClientFallback implements ProductClient {

        @Override
        public String productMsg() {
            return null;
        }

        @Override
        public List<ProductInfoOutput> listForOrder(List<String> productIdList) {
            return null;
        }

        @Override
        public void decreaseStock(List<DecreaseStockInput> decreaseStockInputList) {

        }
    }
}


