package com.example.yourgeekengineer.services;

import com.example.yourgeekengineer.entities.BlogPost;
import com.example.yourgeekengineer.entities.Category;
import com.example.yourgeekengineer.entities.Tag;
import com.example.yourgeekengineer.models.RequestTagModel;
import com.example.yourgeekengineer.repositories.BlogPostRepository;
import com.example.yourgeekengineer.repositories.TagRepository;
import jakarta.persistence.EntityManager;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class TagsService {

    @Autowired
    EntityManager entityManager;

    @Autowired
    private BlogPostRepository blogPostRepository;

    @Autowired
    private TagRepository tagRepository;
    public Set<Tag> newTagsList(List<String> tagsName, BlogPost blogPost, Category category) {
        Set<Tag> allRequiredTags = new HashSet<>();
        for(String tagName : tagsName) {
            tagName = tagName.trim();
            System.out.println(tagName);
            List<Tag> tags = tagRepository.findAllByTagName(tagName);
            Tag newTag = null;
            if(tags.isEmpty())
                newTag = createNewTag(tagName);
            else
                newTag = tags.get(0);
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

    @Transactional
    public List<BlogPost> getAllBlogByTagName(RequestTagModel tagModel) {

        int pageSize = 10;
        Pageable pageable = PageRequest.of(tagModel.getPageNumber(), 10);
        Optional<Tag> tag = tagRepository.findByTagName(tagModel.getTagName());
        if(tag.isPresent()) {
            List<BlogPost> blogs = entityManager.createQuery("SELECT tg.blogPosts FROM Tag tg WHERE tg.id = :tagId", BlogPost.class)
                    .setParameter("tagId", tag.get().getId())
                    .setFirstResult(pageSize * tagModel.getPageNumber())
                    .setMaxResults(pageSize)
                    .getResultList();
            entityManager.close();
            return blogs;
        } else {
            return new ArrayList<>();
        }
    }
}
