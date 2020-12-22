package com.dormakaba.demo.oauth.service;

import com.dormakaba.demo.usercommons.model.entity.User;

public interface UserService {

    User findByUsername(String username);

    User update(User user, Long id);

}
