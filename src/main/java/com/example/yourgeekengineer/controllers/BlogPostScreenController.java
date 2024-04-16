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
    public ResponseEntity<Long> uploadNewBlog(@RequestBody BlogPostModal newBlogPost) throws Exception {
        Long blogId = blogPostService.saveNewBlogPost(newBlogPost);
        return ResponseEntity.ok().body(blogId);
    }

    @GetMapping("/{blog-id}")
    public ResponseEntity<BlogPost> getBlogPost(@PathVariable("blog-id") String blogId) throws Exception{
        return ResponseEntity.ok().body(blogPostService.getBlogPostById(Long.parseLong(blogId)));
    }

    @PostMapping("/like/{blog-id}")
    public ResponseEntity<Integer> updateBlogPostLikes(@PathVariable("blog-id") String blogId) throws Exception {
        return ResponseEntity.ok().body(blogPostService.updateBlogPostLikes(Long.parseLong(blogId)));
    }

    @PostMapping("/dislike/{blog-id}")
    public ResponseEntity<Integer> updateBlogPostDisLikes(@PathVariable("blog-id") String blogId) throws Exception {
        return ResponseEntity.ok().body(blogPostService.updateBlogPostDislikes(Long.parseLong(blogId)));
    }
}
