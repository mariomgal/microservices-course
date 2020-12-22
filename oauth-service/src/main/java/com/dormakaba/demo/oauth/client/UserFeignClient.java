package com.dormakaba.demo.oauth.client;

import com.dormakaba.demo.usercommons.model.entity.User;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.*;

@FeignClient(name = "user-service")
public interface UserFeignClient {

    @GetMapping("/users/search/findByUsername")
    User findByUsername(@RequestParam String username);

    @PutMapping("/users/search/{id}")
    User update(@RequestBody User user, @PathVariable Long id);

}
