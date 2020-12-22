package com.dormakaba.demo.user.model.repository;

import com.dormakaba.demo.usercommons.model.entity.User;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;
import org.springframework.data.rest.core.annotation.RestResource;

@RepositoryRestResource(path = "users")
public interface UserRepository extends PagingAndSortingRepository<User, Long> {

    @RestResource(path = "findByUsername")
    User findByUsername(@Param("username") String username);

}
