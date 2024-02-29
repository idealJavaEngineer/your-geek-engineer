package com.example.yourgeekengineer.repositories;


import com.example.yourgeekengineer.entities.BlogPost;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;


public interface BlogPostRepository extends JpaRepository<BlogPost, Long> {

}
