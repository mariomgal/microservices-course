package com.dormakaba.demo.itemservice.client;

import com.dormakaba.demo.commons.model.entity.Product;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@FeignClient(name = "product-service")
public interface RestProductClient {

    @GetMapping("/list")
    List<Product> list();

    @GetMapping("/details/{id}")
    Product details(@PathVariable Long id);

    @PostMapping("/create")
    Product create(@RequestBody Product product);

    @PutMapping("/edit/{id}")
    Product update(@RequestBody Product product, @PathVariable Long id);

    @DeleteMapping("/delete/{id}")
    void delete(@PathVariable Long id);

}
