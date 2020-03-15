package com.szkingdom.product.server.service.impl;

import com.szkingdom.product.server.dataobject.ProductCategory;
import com.szkingdom.product.server.repository.ProductCategoryRepository;
import com.szkingdom.product.server.service.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @Description 类目服务
 * @Author zhaizhengwei
 */
@Service
public class CategoryServiceImpl implements CategoryService {

    @Autowired
    private ProductCategoryRepository productCategoryRepository;

    @Override
    public List<ProductCategory> findByCategoryTypeIn(List<Integer> categoryTypeList) {
        return productCategoryRepository.findByCategoryTypeIn(categoryTypeList);
    }
}
