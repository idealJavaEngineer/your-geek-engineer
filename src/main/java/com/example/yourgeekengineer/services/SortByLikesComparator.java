package com.example.yourgeekengineer.services;

import com.example.yourgeekengineer.entities.BlogPost;

import java.util.Comparator;

public class SortByLikesComparator implements Comparator<BlogPost> {

    @Override
    public int compare(BlogPost o1, BlogPost o2) {
        return Integer.compare(o2.getLikes(), o1.getLikes());
    }
}
