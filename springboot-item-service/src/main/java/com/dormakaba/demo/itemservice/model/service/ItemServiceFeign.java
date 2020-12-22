package com.dormakaba.demo.itemservice.model.service;

import com.dormakaba.demo.commons.model.entity.Product;
import com.dormakaba.demo.itemservice.client.RestProductClient;
import com.dormakaba.demo.itemservice.model.Item;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service("itemServiceFeign")
public class ItemServiceFeign implements ItemService {

    @Autowired
    private RestProductClient feignClient;

    @Override
    public List<Item> findAll() {
        return feignClient.list().stream().map(p -> new Item(p, 1)).collect(Collectors.toList());
    }

    @Override
    public Item findById(Long id, Integer quantity) {
        return new Item(feignClient.details(id), quantity);
    }

    @Override
    public Product create(Product product) {
        return feignClient.create(product);
    }

    @Override
    public Product update(Product product, Long id) {
        return feignClient.update(product, id);
    }

    @Override
    public void delete(Long id) {
        feignClient.delete(id);
    }
}
