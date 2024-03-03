package com.example.yourgeekengineer.models;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class RequestCategoryModel {

    private String categoryName;
    private String pageNumber;
    private String tagId;

    public RequestCategoryModel(String categoryName, String pageNumber, String tagId) {
        this.categoryName = categoryName;
        this.pageNumber = pageNumber;
        this.tagId = tagId;
    }
}
