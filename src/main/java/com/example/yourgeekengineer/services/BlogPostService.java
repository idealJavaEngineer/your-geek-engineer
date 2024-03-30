package com.example.yourgeekengineer.services;

import com.example.yourgeekengineer.entities.Author;
import com.example.yourgeekengineer.entities.BlogPost;
import com.example.yourgeekengineer.entities.Category;
import com.example.yourgeekengineer.models.BlogPostModal;
import com.example.yourgeekengineer.repositories.BlogPostRepository;
import jakarta.persistence.EntityManager;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
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

    @Transactional
    private List<BlogPost> findBlogsByCategory(Category category, Pageable page) {
        List<BlogPost> blogs = entityManager.createQuery(
                        "SELECT c.blogPosts FROM Category c WHERE c.id = :categoryId",
                        BlogPost.class)
                .setParameter("categoryId", category.getCategoryId())
                .setFirstResult(page.getPageNumber() * page.getPageSize())
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

    public BlogPost getBlogPostById(Long id) throws Exception {
        Optional<BlogPost> blogPost = blogPostRepository.findById(id);
        if (blogPost.isPresent()) {
            BlogPost currBlogPost = blogPost.get();
            Author author = currBlogPost.getAuthor();
            author.getUser().setEmail("");
            author.getUser().setUserId(-1);
            currBlogPost.setAuthor(author);
            return currBlogPost;
        }
        throw new Exception("NO BLOG POST EXIST AT THE REQUIRED PAGE.");
    }

    public int updateBlogPostLikes(Long blogId) throws Exception {
        //find blogpost, update it and save it.
        Optional<BlogPost> blogPost = blogPostRepository.findById(blogId);
        if(blogPost.isPresent()) {
            BlogPost currBlog = blogPost.get();
            int currLikes = currBlog.getLikes()+1;
            currBlog.setLikes(currLikes);
            blogPostRepository.save(currBlog);
            return currLikes;
        } else {
            throw new Exception("blog post related to this id is not present.");
        }
    }

    public int updateBlogPostDislikes(Long blogId) throws Exception {
        //find blogpost, update it and save it.
        Optional<BlogPost> blogPost = blogPostRepository.findById(blogId);
        if(blogPost.isPresent()) {
            BlogPost currBlog = blogPost.get();
            int currDislikes = currBlog.getDislikes()+1;
            currBlog.setDislikes(currDislikes);
            blogPostRepository.save(currBlog);
            return currDislikes;
        } else {
            throw new Exception("blog post related to this id is not present.");
        }
    }

    public List<BlogPost> getAllBlogPost() {
        List<BlogPost> allBlogPost = blogPostRepository.findAllByOrderByCreatedAtDesc();
        return allBlogPost;
    }
}
