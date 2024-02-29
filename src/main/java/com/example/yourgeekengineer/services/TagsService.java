package com.example.yourgeekengineer.services;

import com.example.yourgeekengineer.entities.BlogPost;
import com.example.yourgeekengineer.entities.Category;
import com.example.yourgeekengineer.entities.Tag;
import com.example.yourgeekengineer.repositories.TagRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class TagsService {

    @Autowired
    private TagRepository tagRepository;
    public List<Tag> newTagsList(List<String> tagsName, BlogPost blogPost, Category category) {
        List<Tag> allRequiredTags = new ArrayList<>();
        for(String tagName : tagsName) {
            Optional<Tag> tag = tagRepository.findByTagName(tagName);
            Tag newTag = null;
            if(tag.isEmpty())
                newTag = createNewTag(tagName);
            else
                newTag = tag.get();
            newTag.getBlogPosts().add(blogPost);
            category.getTags().add(newTag);
            allRequiredTags.add(newTag);
        }
        return allRequiredTags;
    }

    public Tag createNewTag(String tagName) {
        Tag newTag = new Tag();
        newTag.setTagName(tagName);
        return newTag;
    }
}
