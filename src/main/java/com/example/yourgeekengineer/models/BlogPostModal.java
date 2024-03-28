package com.example.yourgeekengineer.models;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class BlogPostModal {

    private String blogName;
    private String userId;
    private String tags;
    private String category;
    private String blogContent;
    private String imageUrl;

    public BlogPostModal(String blogName, String userId, String tags, String category, String blogContent, String imageUrl) {
        this.blogName = blogName;
        this.userId = userId;
        this.tags = tags;
        this.category = category;
        this.blogContent = blogContent;
        this.imageUrl = imageUrl;
    }
}
