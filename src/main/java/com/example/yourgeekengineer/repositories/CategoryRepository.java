package com.example.yourgeekengineer.repositories;


import com.example.yourgeekengineer.entities.BlogPost;
import com.example.yourgeekengineer.entities.Category;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.Optional;

public interface CategoryRepository extends JpaRepository<Category, Long> {

    Optional<Category> findByCategoryName(String categoryName);

//    @Query("SELECT c.blogPosts FROM Category c Where c.categoryName= ?1 ORDER BY c.blogPosts.likes DESC LIMIT 3")
//    public List<BlogPost> findTopBlogsByLikesDescByCategory(String categoryName);
}
