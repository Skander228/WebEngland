package com.englan.Blog.repo;

import com.englan.Blog.models.Baby;
import com.englan.Blog.models.User;
import org.springframework.data.jpa.repository.JpaRepository;

// CrudRepository интерфейс который принимает 2 порамметра, класс Users и тип поле для id
//
public interface UserRepository extends JpaRepository<User, Long> {
    User findByUsern(String usern);
    User findByEmail(String email);
}