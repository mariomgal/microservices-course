package com.dormakaba.demo.productservice.controller;

import com.dormakaba.demo.commons.model.entity.Product;
import com.dormakaba.demo.productservice.model.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
public class ProductController {

    @Value("${server.port}")
    private Integer port;

    @Autowired
    private ProductService productService;

    @GetMapping("/list")
    public List<Product> list() {
        return productService.findAll().stream()
                .peek(p -> p.setPort(port)
                ).collect(Collectors.toList());
    }

    @GetMapping("/details/{id}")
    public Product details(@PathVariable Long id) {
        Product product = productService.findById(id);
        product.setPort(port);
        return productService.findById(id);
    }

    @PostMapping("/create")
    @ResponseStatus(HttpStatus.CREATED)
    public Product create(@RequestBody Product product) {
        return productService.save(product);
    }

    @PutMapping("/edit/{id}")
    @ResponseStatus(HttpStatus.CREATED)
    public Product edit(@RequestBody Product product, @PathVariable Long id) {
        Product oldProduct = productService.findById(id);
        oldProduct.setName(product.getName());
        oldProduct.setPrice(product.getPrice());
        return productService.save(oldProduct);
    }

    @DeleteMapping("/delete/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void delete(@PathVariable Long id) {
        productService.deleteById(id);
    }

}
