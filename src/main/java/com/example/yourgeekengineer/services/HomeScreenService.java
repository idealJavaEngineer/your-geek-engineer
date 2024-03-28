package com.example.yourgeekengineer.services;

import com.example.yourgeekengineer.entities.BlogPost;
import com.example.yourgeekengineer.enums.CategoryEnum;
import com.example.yourgeekengineer.repositories.BlogPostRepository;
import com.example.yourgeekengineer.repositories.CategoryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class HomeScreenService {

    @Autowired
    private BlogPostService blogPostService;

    @Autowired
    private BlogPostRepository blogPostRepository;

    @Autowired
    private CategoryRepository categoryRepository;

    public Map<String, List<BlogPost>> getInitialData() {
        Map<String, List<BlogPost>> responseData = new HashMap<>();
        responseData.put("mostLiked", blogPostRepository.findTopBlogsByLikesDesc());
        List<BlogPost> tutorialBLogPostList = categoryRepository.findByCategoryName(CategoryEnum.Tutorial.name()).get().getBlogPosts();
        List<BlogPost> journalBLogPostList = categoryRepository.findByCategoryName(CategoryEnum.Journal.name()).get().getBlogPosts();
        List<BlogPost> reviewBLogPostList = categoryRepository.findByCategoryName(CategoryEnum.Review.name()).get().getBlogPosts();
        Collections.sort(tutorialBLogPostList, new SortByLikesComparator());
        Collections.sort(journalBLogPostList, new SortByLikesComparator());
        Collections.sort(reviewBLogPostList, new SortByLikesComparator());

        responseData.put("mostLikedTutorials", tutorialBLogPostList.subList(0, Math.min(tutorialBLogPostList.size(), 3)));
        responseData.put("mostLikedJournals", journalBLogPostList.subList(0, Math.min(journalBLogPostList.size(), 3)));
        responseData.put("mostLikedReviews", reviewBLogPostList.subList(0, Math.min(reviewBLogPostList.size(), 3)));

        Collections.sort(tutorialBLogPostList, new SortByCreatedDateComparatorService());
        Collections.sort(journalBLogPostList, new SortByCreatedDateComparatorService());
        Collections.sort(reviewBLogPostList, new SortByCreatedDateComparatorService());

        responseData.put("latestTutorials", tutorialBLogPostList.subList(0, Math.min(tutorialBLogPostList.size(), 3)));
        responseData.put("latestJournals", journalBLogPostList.subList(0, Math.min(journalBLogPostList.size(), 3)));
        responseData.put("latestReviews", reviewBLogPostList.subList(0, Math.min(reviewBLogPostList.size(), 3)));
        return responseData;
    }
}
