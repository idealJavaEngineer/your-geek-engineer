package com.example.yourgeekengineer.models;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class RequestCategoryModel {

    private String categoryName;
    private String pageNumber;

    public RequestCategoryModel(String categoryName, String pageNumber) {
        this.categoryName = categoryName;
        this.pageNumber = pageNumber;
    }
}
