package com.example.yourgeekengineer.models;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class SearchBlogPostInfo {

    private long id;
    private String blogName;

    public SearchBlogPostInfo(long id, String blogName) {
        this.id = id;
        this.blogName = blogName;
    }
}
