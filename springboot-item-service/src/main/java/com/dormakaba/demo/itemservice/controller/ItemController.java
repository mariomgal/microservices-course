package com.dormakaba.demo.itemservice.controller;

import com.dormakaba.demo.commons.model.entity.Product;
import com.dormakaba.demo.itemservice.model.Item;
import com.dormakaba.demo.itemservice.model.service.ItemService;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.core.env.Environment;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RefreshScope
public class ItemController {

    @Autowired
    private Environment env;

    @Autowired
    @Qualifier("itemServiceFeign")
    private ItemService itemService;

    @GetMapping("/list")
    public List<Item> list() {
        return itemService.findAll();
    }

    @HystrixCommand(fallbackMethod = "detailsAlternative")
    @GetMapping("/details/{id}/quantity/{quantity}")
    public Item details(@PathVariable Long id, @PathVariable Integer quantity) {
        return itemService.findById(id, quantity);
    }

    @GetMapping("/config")
    public ResponseEntity<?> configText(@Value("${server.port}") String port) {
        Map<String, String> config = new HashMap<>();
//        config.put("config", configText);
//        config.put("port", port);
//        if (env.getActiveProfiles().length > 0 && env.getActiveProfiles()[0].equals("dev")) {
//            config.put("author.name", env.getProperty("config.author.name"));
//            config.put("author.email", env.getProperty("config.author.email"));
//        }
        return new ResponseEntity<>(config, HttpStatus.OK);
    }

    @PostMapping("/create")
    @ResponseStatus(HttpStatus.CREATED)
    public Product create(@RequestBody Product product) {
        return itemService.create(product);
    }

    @PutMapping("/edit/{id}")
    @ResponseStatus(HttpStatus.CREATED)
    public Product update(@RequestBody Product product, @PathVariable Long id) {
        return itemService.update(product, id);
    }

    @DeleteMapping("/delete/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void delete(@PathVariable Long id) {
        itemService.delete(id);
    }

    public Item detailsAlternative(Long id, Integer quantity) {
        Product product = new Product();
        product.setId(id);
        product.setName("Placeholder item");
        product.setPrice(0.0);
        Item item = new Item(product, 0);
        return item;
    }

}
