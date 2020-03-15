package com.szkingdom.order.server.controller;


import com.netflix.hystrix.HystrixCommandProperties;
import com.netflix.hystrix.contrib.javanica.annotation.DefaultProperties;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixProperty;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import java.util.Arrays;

/**
 * @Description 降级
 * @Author zhaizhengwei
 */
@RestController
@DefaultProperties(defaultFallback = "defaultFallback")
public class HystrixController {

    // 超时降级设置
//    @HystrixCommand(commandProperties =  {
//        @HystrixProperty(name = "execution.isolation.thread.timeoutInMilliseconds", value = "3000")
//    })//(fallbackMethod = "fallback")

    // 熔断设置
//    @HystrixCommand(commandProperties =  {
//            @HystrixProperty(name = "circuitBreaker.enabled", value = "true"),
//            @HystrixProperty(name = "circuitBreaker.requestVolumeThreshold", value = "10"),
//            @HystrixProperty(name = "circuitBreaker.sleepWindowInMilliseconds", value = "10000"),
//            @HystrixProperty(name = "circuitBreaker.errorThresholdPercentage", value = "60")
//    })

    @HystrixCommand
    @GetMapping("/getProductInfoList")
    public String getProductInfoList(@RequestParam("number") Integer number) {
        if(number % 2 == 0) {
            return "success";
        }

        RestTemplate restTemplate = new RestTemplate();
        return restTemplate.postForObject("http://localhost:8082/product/listForOrder",
                Arrays.asList("15875196366160022"), String.class);
//        throw new RuntimeException();
    }

    private String fallback() {
        return "太拥挤了，请稍后重试~~";
    }

    private String defaultFallback() {
        return "太拥挤了，请稍后重试~~";
    }
}
