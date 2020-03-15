package com.szkingdom.order.server.controller;

import com.szkingdom.product.client.ProductClient;
import com.szkingdom.product.common.DecreaseStockInput;
import com.szkingdom.product.common.ProductInfoOutput;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import java.util.Arrays;
import java.util.List;

/**
 * @Description
 * @Author zhaizhengwei
 */
@RestController
@Slf4j
public class ClientController {

//    @Autowired
//    private LoadBalancerClient loadBalancerClient;

    @Autowired
    private RestTemplate restTemplate;

    @Autowired
    private ProductClient productClient;

    @GetMapping("/getProductMsg")
    public String getProductMsg() {

        //第一种方式(直接使用RestTemplate，url写死)
//        RestTemplate restTemplate = new RestTemplate();
//        String response = restTemplate.getForObject("http://localhost:9000/msg", String.class);

        //第二种方式（使用LoadBalancerClient获取服务的host和port，拼接url，再使用RestTemplate）
//        RestTemplate restTemplate = new RestTemplate();
//        ServiceInstance serviceInstance = loadBalancerClient.choose("PRODUCT");
//        String url = String.format("http://%s:%s", serviceInstance.getHost(), serviceInstance.getPort()) + "/msg";
//        String response = restTemplate.getForObject(url, String.class);

        //第三种方式（给RestTemplate配置注解@LoadBalanced，通过“应用名”访问服务）
        String response = restTemplate.getForObject("http://PRODUCT/msg", String.class);

        log.info("response: {}", response);
        return response;

    }

    @GetMapping("/getProductList")
    public String getProductList() {
        List<ProductInfoOutput> productInfoList = productClient.listForOrder(Arrays.asList("15875196366160022"));
        log.info("response= {}", productInfoList);
        return "ok";
    }

    @GetMapping("/productDecreaseStock")
    public String productDecreaseStock() {
        productClient.decreaseStock(Arrays.asList(new DecreaseStockInput("16103465734242707", 3)));
        return "ok";
    }
}
