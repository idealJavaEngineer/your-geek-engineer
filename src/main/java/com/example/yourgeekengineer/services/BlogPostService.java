package com.example.yourgeekengineer.services;

import com.example.yourgeekengineer.entities.BlogPost;
import com.example.yourgeekengineer.entities.Category;
import com.example.yourgeekengineer.models.BlogPostModal;
import com.example.yourgeekengineer.repositories.BlogPostRepository;
import jakarta.persistence.EntityManager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.logging.Logger;

@Service
public class BlogPostService {
    public static final Logger logger = Logger.getLogger(BlogPostService.class.getName());

    @Autowired
    private BlogPostRepository blogPostRepository;

    @Autowired
    private EntityManager entityManager;

    @Autowired
    private RefactorNewBlogPostService refactorNewBlogPostService;

    public List<BlogPost> getBlogPostByCategory(Category category, Pageable page) {

        return findBlogsByCategory(category, page);
    }

    private List<BlogPost> findBlogsByCategory(Category category, Pageable page) {
        List<BlogPost> blogs = entityManager.createQuery(
                        "SELECT bp FROM BlogPost bp JOIN bp.categories c WHERE c.id = :categoryId",
                        BlogPost.class)
                .setParameter("categoryId", category.getCategoryId())
                .setFirstResult(page.getPageNumber())
                .setMaxResults(page.getPageSize())
                .getResultList();
        entityManager.close();
        System.out.println(blogs.size());
        return blogs;
    }

    public void saveNewBlogPost(BlogPostModal blogPostModal) throws Exception {
        BlogPost newBlog = refactorNewBlogPostService.refactorBlogPostModalToEntity(blogPostModal);
        newBlog = blogPostRepository.save(newBlog);
        logger.info("blog is successfully saved");
        System.out.println(newBlog);
    }
}
