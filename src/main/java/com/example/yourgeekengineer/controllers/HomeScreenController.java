package com.example.yourgeekengineer.controllers;

import com.example.yourgeekengineer.entities.BlogPost;
import com.example.yourgeekengineer.services.HomeScreenService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Map;

@RestController
@CrossOrigin("*")
@RequestMapping("/home-screen")
public class HomeScreenController {

    @Autowired
    private HomeScreenService homeScreenService;

    @GetMapping("")
    public ResponseEntity<Map<String, List<BlogPost>>> getInitialHomeScreenData() {
        return ResponseEntity.ok().body(homeScreenService.getInitialData());
    }
}
