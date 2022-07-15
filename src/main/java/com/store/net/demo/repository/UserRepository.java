package com.store.net.demo.repository;

import com.store.net.demo.entity.User;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;


public interface UserRepository extends JpaRepository<User, Long> {

    @Query(value = "Select u from User u where u.name like %?1%")
    Page<User> findByName(String name, Pageable page);

    Page<User> findAll(Pageable page);

}
