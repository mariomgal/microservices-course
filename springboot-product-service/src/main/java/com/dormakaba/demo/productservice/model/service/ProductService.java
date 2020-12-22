package com.dormakaba.demo.productservice.model.service;

import com.dormakaba.demo.commons.model.entity.Product;

import java.util.List;

public interface ProductService {

    List<Product> findAll();

    Product findById(Long id);

    Product save(Product product);

    void deleteById(Long id);

}
