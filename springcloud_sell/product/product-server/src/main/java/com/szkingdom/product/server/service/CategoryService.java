package com.szkingdom.product.server.service;

import com.szkingdom.product.server.dataobject.ProductCategory;

import java.util.List;

/**
 * @Description 类目服务
 * @Author zhaizhengwei
 */
public interface CategoryService {

    List<ProductCategory> findByCategoryTypeIn(List<Integer> categoryTypeList);
}
