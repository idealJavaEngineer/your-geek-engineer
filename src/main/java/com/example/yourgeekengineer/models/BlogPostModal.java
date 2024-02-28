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

    public BlogPostModal(String blogName, String userId, String tags, String category, String blogContent) {
        this.blogName = blogName;
        this.userId = userId;
        this.tags = tags;
        this.category = category;
        this.blogContent = blogContent;
    }

    @Override
    public String toString() {
        return "BlogPostModal{" +
                "blogName='" + blogName + '\'' +
                ", userId='" + userId + '\'' +
                ", tags='" + tags + '\'' +
                ", category='" + category + '\'' +
                ", content='" + blogContent + '\'' +
                '}';
    }
}
