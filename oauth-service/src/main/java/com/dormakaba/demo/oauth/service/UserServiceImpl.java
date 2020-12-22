package com.dormakaba.demo.oauth.service;

import brave.Tracer;
import com.dormakaba.demo.oauth.client.UserFeignClient;
import feign.FeignException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class UserServiceImpl implements UserDetailsService, UserService {

    private static final Logger LOG = LoggerFactory.getLogger(UserServiceImpl.class);

    @Autowired
    private UserFeignClient userClient;

    @Autowired
    private Tracer tracer;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        try {
            com.dormakaba.demo.usercommons.model.entity.User user = userClient.findByUsername(username);
            if (user == null) {
                LOG.error("Username {0} not found", username);
                throw new UsernameNotFoundException("Invalid username");
            }
            List<GrantedAuthority> authorities = user.getRoles().stream().map(r -> new SimpleGrantedAuthority(r.getName()))
                    .collect(Collectors.toList());
            return new User(user.getUsername(), user.getPassword(), user.getEnabled(), true, true, true, authorities);
        } catch (FeignException e) {
            tracer.currentSpan().tag("error.message", e.getMessage());
            throw new UsernameNotFoundException(e.getMessage());
        }
    }

    @Override
    public com.dormakaba.demo.usercommons.model.entity.User update(com.dormakaba.demo.usercommons.model.entity.User user, Long id) {
        return userClient.update(user, id);
    }

    @Override
    public com.dormakaba.demo.usercommons.model.entity.User findByUsername(String username) {
        return userClient.findByUsername(username);
    }

}
