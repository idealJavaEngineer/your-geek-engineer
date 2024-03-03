package com.example.yourgeekengineer.services;

import com.example.yourgeekengineer.entities.BlogPost;
import com.example.yourgeekengineer.entities.Category;
import com.example.yourgeekengineer.entities.Tag;
import com.example.yourgeekengineer.models.RequestCategoryModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class CategoryScreenService {

    private static final String DEFAULT_TAG = "trending";

    @Autowired
    CategoryService categoryService;

    @Autowired
    BlogPostService blogPostService;

    public Map<String, Object> getAllScreenData(RequestCategoryModel category) {
        Map<String, Object> requiredData = new HashMap<>();
        Sort sort = Sort.by(Sort.Direction.DESC, "blogId");
        Pageable pageable = PageRequest.of(Integer.parseInt(category.getPageNumber()), 10, sort);
        Category requiredCategory = categoryService.getCategoryByCategoryName(category);
        Set<Tag> tags = categoryService.getAllTagsOfCategory(requiredCategory);
        requiredData.put("tags",tags);
        if(!checkIfDefaultTagValid(Long.parseLong(category.getTagId()), tags)) {
            requiredData.put("blogs", blogPostService.getBlogPostByCategory(requiredCategory, pageable));
            Tag defaultTag = new Tag();
            defaultTag.setTagName("trending");
            defaultTag.setId(-1);
            requiredData.put("defaultTag", defaultTag);
        } else {
            List<BlogPost> blogs = blogPostService.getBlogPostByCategory(requiredCategory, pageable);
            int size = blogs.size();
            List<BlogPost> newBlogs = new ArrayList<>();
            for(BlogPost blog : blogs) {
                if(findTagInTagList(Long.parseLong(category.getTagId()), blog.getTags()) != null) {
                    newBlogs.add(blog);
                }
            }
            requiredData.put("defaultTag", findTagInTagList(Long.parseLong(category.getTagId()), tags));
            requiredData.put("blogs", newBlogs);
        }
        return requiredData;
    }

    private boolean checkIfDefaultTagValid(long tagId, Set<Tag> tags) {
        if(tagId == -1)
            return false;
        return findTagInTagList(tagId, tags) != null;
    }

    private Tag findTagInTagList(long tagId, Set<Tag> tags) {
        for (Tag currTag : tags) {
            if (currTag.getId() == tagId) {
                return currTag;
            }
        }
        return null;
    }

}
