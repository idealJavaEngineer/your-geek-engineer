package com.example.yourgeekengineer.services;

import com.example.yourgeekengineer.entities.Category;
import com.example.yourgeekengineer.models.RequestCategoryModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;

@Service
public class CategoryScreenService {

    private static final String DEFAULT_TAG = "trending";

    @Autowired
    CategoryService categoryService;

    @Autowired
    BlogPostService blogPostService;

    public Map<String, Object> getAllScreenData(RequestCategoryModel category) {
        Map<String, Object> requiredData = new HashMap<>();
        Pageable pageable = PageRequest.of(Integer.parseInt(category.getPageNumber()), 10);
        Category requiredCategory = categoryService.getCategoryByCategoryName(category);
        requiredData.put("tags", categoryService.getAllTagsOfCategory(requiredCategory));
        requiredData.put("blogs", blogPostService.getBlogPostByCategory(requiredCategory, pageable));
        requiredData.put("defaultTag", categoryService.getFirstValidTag(requiredCategory) );
        return requiredData;
    }
}
