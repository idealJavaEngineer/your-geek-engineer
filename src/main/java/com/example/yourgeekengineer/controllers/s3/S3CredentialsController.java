package com.example.yourgeekengineer.controllers.s3;

import com.example.yourgeekengineer.services.s3.ImageCrudService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.ArrayList;
import java.util.List;

@RestController
@CrossOrigin("*")
@RequestMapping("/aws-s3")
public class S3CredentialsController {

    @Autowired
    private ImageCrudService imageCrudService;

    @PostMapping("/credentials/upload-file")
    public ResponseEntity<String> getCredentials(@RequestParam("file") MultipartFile multipartFile) throws Exception {
        String fileName = imageCrudService.uploadFile(multipartFile);

        return ResponseEntity.ok().body(fileName);
    }
}
