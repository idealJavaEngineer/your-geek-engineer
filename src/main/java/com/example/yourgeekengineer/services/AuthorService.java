package com.example.yourgeekengineer.services;

import com.example.yourgeekengineer.entities.Author;
import com.example.yourgeekengineer.repositories.AuthorRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class AuthorService {

    @Autowired
    private AuthorRepository authorRepository;

    public Author getAuthorByUserId(String userId) throws Exception {
        Optional<Author> author = authorRepository.findByUser_userId(Long.valueOf(userId));
        if(author.isPresent())
            return author.get();
        else
            throw new Exception("author doesn't exist");
    }
}
