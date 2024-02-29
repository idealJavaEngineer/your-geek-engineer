package com.example.yourgeekengineer.models;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class RequestTagModel {

    String tagName;
    int pageNumber;

    public RequestTagModel(String tagName, int pageNumber) {
        this.tagName = tagName;
        this.pageNumber = pageNumber;
    }

}
