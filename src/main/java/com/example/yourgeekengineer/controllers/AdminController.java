package com.example.yourgeekengineer.controllers;

import com.example.yourgeekengineer.models.BlogPostModal;
import com.example.yourgeekengineer.models.UserCredentialModal;
import com.example.yourgeekengineer.services.BlogPostService;
import com.example.yourgeekengineer.services.UserValidationService;
import org.springframework.beans.factory.annotation.Autowire;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@CrossOrigin("*")
@RequestMapping("admin")
public class AdminController {


    @Autowired
    UserValidationService userValidationService;

    @PostMapping("login")
    public boolean adminLogin(@RequestBody UserCredentialModal credential) {
        return userValidationService.adminUserValidation(credential);
    }

}
