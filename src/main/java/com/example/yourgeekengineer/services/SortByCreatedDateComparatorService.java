package com.example.yourgeekengineer.services;

import com.example.yourgeekengineer.entities.BlogPost;
import com.example.yourgeekengineer.entities.Category;

import java.sql.Timestamp;
import java.util.Comparator;

public class SortByCreatedDateComparatorService implements Comparator<BlogPost> {

    @Override
    public int compare(BlogPost o1, BlogPost o2) {
        Timestamp bp1 = o1.getCreatedAt();
        Timestamp bp2 = o2.getCreatedAt();
        return bp2.compareTo(bp1);
    }
}
