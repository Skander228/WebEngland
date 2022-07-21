package com.englan.Blog.repo;

import com.englan.Blog.models.Baby;
import org.springframework.data.repository.CrudRepository;

public interface BabyRepository extends CrudRepository<Baby, Long> {

}