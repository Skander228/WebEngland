package com.englan.Blog.repo;

import com.englan.Blog.models.Baby;
import com.englan.Blog.models.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface BabyRepository extends JpaRepository<Baby, Long> {
    List<Baby> getAllByUserId(User userId);
}