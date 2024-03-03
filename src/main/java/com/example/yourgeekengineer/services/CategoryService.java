package com.example.yourgeekengineer.services;

import com.example.yourgeekengineer.entities.Category;
import com.example.yourgeekengineer.entities.Tag;
import com.example.yourgeekengineer.models.RequestCategoryModel;
import com.example.yourgeekengineer.repositories.CategoryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.Set;

@Service
public class CategoryService {

    @Autowired
    private CategoryRepository categoryRepository;

    public Set<Tag> getAllTagsOfCategory(Category category) {
        return category.getTags();
    }

    public Tag getFirstValidTag(Category category) {
        return getAllTagsOfCategory(category).iterator().next();
    }

    public Category getCategoryByCategoryName(RequestCategoryModel category) {
        Category requiredCategory = null;
        if (category.getCategoryName() != null) {
            Optional<Category> obj = categoryRepository.findByCategoryName(category.getCategoryName());
            if (obj.isPresent())
                requiredCategory = obj.get();
        }
        return requiredCategory;
    }

    public Category getCategoryByCategoryName(String categoryName) throws Exception {
        Optional<Category> obj = categoryRepository.findByCategoryName(categoryName);
        if (obj.isPresent())
            return obj.get();
        else
            throw new Exception("no such category exist");
    }
}
