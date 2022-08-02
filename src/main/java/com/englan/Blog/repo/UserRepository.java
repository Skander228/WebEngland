package com.englan.Blog.repo;

import com.englan.Blog.models.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

// CrudRepository интерфейс который принимает 2 порамметра, класс Users и тип поле для id
//
public interface UserRepository extends JpaRepository<User, Long> {
    User findByUsern(String usern);
    User findByEmail(String email);
}