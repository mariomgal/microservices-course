package com.dormakaba.demo.itemservice.model.service;

import com.dormakaba.demo.commons.model.entity.Product;
import com.dormakaba.demo.itemservice.model.Item;

import java.util.List;

public interface ItemService {

    List<Item> findAll();

    Item findById(Long id, Integer quantity);

    Product create(Product product);

    Product update(Product product, Long id);

    void delete(Long id);

}
