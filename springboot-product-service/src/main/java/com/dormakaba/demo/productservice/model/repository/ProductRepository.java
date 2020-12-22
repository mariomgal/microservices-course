package com.dormakaba.demo.productservice.model.repository;

import com.dormakaba.demo.commons.model.entity.Product;
import org.springframework.data.repository.CrudRepository;

public interface ProductRepository extends CrudRepository<Product, Long> {
}
