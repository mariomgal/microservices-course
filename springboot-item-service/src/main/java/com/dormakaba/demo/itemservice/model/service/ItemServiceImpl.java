package com.dormakaba.demo.itemservice.model.service;


import com.dormakaba.demo.commons.model.entity.Product;
import com.dormakaba.demo.itemservice.model.Item;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Service("itemServiceRestTemplate")
public class ItemServiceImpl implements ItemService {

    @Autowired
    @Qualifier("restTemplate")
    RestTemplate restTemplate;

    @Override
    public List<Item> findAll() {
        List<Product> products =  Arrays.asList(restTemplate.getForObject("http://product-service/list", Product[].class));
        return products.stream().map(p -> new Item(p, 1)).collect(Collectors.toList());
    }

    @Override
    public Item findById(Long id, Integer quantity) {
        Map<String, String> parameters = new HashMap<>();
        parameters.put("id", id.toString());
        Product product = restTemplate.getForObject("http://product-service/details/{id}", Product.class, parameters);
        return new Item(product, quantity);
    }

    @Override
    public Product create(Product product) {
        HttpEntity<Product> body = new HttpEntity<>(product);
        return restTemplate.exchange("http://product-service/create", HttpMethod.POST, body, Product.class).getBody();
    }

    @Override
    public Product update(Product product, Long id) {
        HttpEntity<Product> body = new HttpEntity<>(product);
        Map<String, String> parameters = new HashMap<>();
        parameters.put("id", id.toString());
        return restTemplate.exchange("http://product-service/edit/{id}", HttpMethod.PUT, body,
                Product.class, parameters).getBody();
    }

    @Override
    public void delete(Long id) {
        Map<String, String> parameters = new HashMap<>();
        parameters.put("id", id.toString());
        restTemplate.delete("http://product-service/delete/{id}", parameters);
    }

}
