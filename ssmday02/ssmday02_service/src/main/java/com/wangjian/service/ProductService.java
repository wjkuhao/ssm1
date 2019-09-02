package com.wangjian.service;

import com.wangjian.Product;

import java.util.List;

public interface ProductService {
    List<Product> findAll();
    void save(Product product);
}
