package com.englan.Blog.repo;

import com.englan.Blog.models.User;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

// CrudRepository интерфейс который принимает 2 порамметра, класс Users и тип поле для id
//
public interface UserRepository extends CrudRepository<User, Long> {
    List<User> findAllByOrderByIdDesc();
    User findByEmail(String email);
}