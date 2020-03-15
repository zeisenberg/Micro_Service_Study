package com.szkingdom.product.server.service;

import com.szkingdom.product.server.ProductApplicationTests;
import com.szkingdom.product.common.DecreaseStockInput;
import com.szkingdom.product.common.ProductInfoOutput;
import com.szkingdom.product.server.dataobject.ProductInfo;
import org.junit.Assert;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Arrays;
import java.util.List;

/**
 * @Description
 * @Author zhaizhengwei
 */
@Component
public class ProductServiceTest extends ProductApplicationTests{

    @Autowired
    private ProductService productService;

    @Test
    public void findUpAll() throws Exception {
        List<ProductInfo> list = productService.findUpAll();
        Assert.assertTrue(list.size() > 0);
    }

    @Test
    public void findList() throws Exception {
        List<ProductInfoOutput> list = productService.findList(Arrays.asList("15875196366160022", "15875227953464068"));
        Assert.assertTrue(list.size() > 0);
    }

    @Test
    public void decreaseStock() throws Exception {
        DecreaseStockInput decreaseStockInput = new DecreaseStockInput("15875196366160022", 2);
        productService.decreaseStock(Arrays.asList(decreaseStockInput));
    }

}