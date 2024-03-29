package com.example.yourgeekengineer.controllers;

import com.example.yourgeekengineer.entities.BlogPost;
import com.example.yourgeekengineer.models.BlogPostModal;
import com.example.yourgeekengineer.services.BlogPostService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@RestController
@CrossOrigin("*")
@RequestMapping("/blog-post")
public class BlogPostScreenController {

    @Autowired
    BlogPostService blogPostService;

    @PostMapping("/upload-blog")
    public ResponseEntity<Boolean> uploadNewBlog(@RequestBody BlogPostModal newBlogPost) throws Exception {
        blogPostService.saveNewBlogPost(newBlogPost);
        return ResponseEntity.ok().body(true);
    }

    @GetMapping("/{blog-id}")
    public ResponseEntity<BlogPost> getBlogPost(@PathVariable("blog-id") String blogId) throws Exception{
        return ResponseEntity.ok().body(blogPostService.getBlogPostById(Long.parseLong(blogId)));
    }
}
