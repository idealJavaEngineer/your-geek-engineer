package com.example.yourgeekengineer.repositories;


import com.example.yourgeekengineer.entities.BlogPost;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;


public interface BlogPostRepository extends JpaRepository<BlogPost, Long> {

    @Query("SELECT bp FROM BlogPost bp ORDER BY bp.createdAt DESC")
    public List<BlogPost> findAllByOrderByCreatedAtDesc();

    @Query("SELECT bp FROM BlogPost bp ORDER BY bp.likes DESC LIMIT 5")
    public List<BlogPost> findTopBlogsByLikesDesc();

//    @Query("SELECT bp FROM BlogPost bp JOIN FETCH bp.category c WHERE c.categoryName = ?1 ORDER BY bp.likes DESC")
//    List<BlogPost> findTopBlogsByLikesDescByCategory(String categoryName);
}
