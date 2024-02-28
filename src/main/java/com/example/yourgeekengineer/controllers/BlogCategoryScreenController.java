package com.example.yourgeekengineer.controllers;


import com.example.yourgeekengineer.models.RequestCategoryModel;
import com.example.yourgeekengineer.services.CategoryScreenService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
@CrossOrigin("*")
@RequestMapping("/category")
public class BlogCategoryScreenController {

    @Autowired
    private CategoryScreenService categoryScreenService;

    @GetMapping("/info")
    public ResponseEntity<Map<String, Object>> getBlogsScreenInfo(@RequestBody RequestCategoryModel category) throws Exception {
        try {
            return ResponseEntity.ok().body(categoryScreenService.getAllScreenData(category));
        } catch (Exception e) {
            throw new Exception("not able to perform operation");
        }
    }
}
