package com.example.yourgeekengineer.services;

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
    public List<Tag> addNewTags(List<String> tagsName) {
        List<Tag> allRequiredTags = new ArrayList<>();
        for(String tagName : tagsName) {
            Optional<Tag> tag = tagRepository.findByTagName(tagName);
            if(tag.isEmpty()) {
                Tag newTag = saveNewTag(tagName);
                allRequiredTags.add(newTag);
            } else {
                allRequiredTags.add(tag.get());
            }
        }
        return allRequiredTags;
    }

    public Tag saveNewTag(String tagName) {
        Tag newTag = new Tag();
        newTag.setTagName(tagName);
        return tagRepository.save(newTag);
    }
}
