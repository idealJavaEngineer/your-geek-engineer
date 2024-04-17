package com.example.yourgeekengineer.controllers.s3;

import com.example.yourgeekengineer.models.UploadBlogCoverImage;
import com.example.yourgeekengineer.services.BlogPostService;
import com.example.yourgeekengineer.services.s3.ImageCrudService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
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

    @Autowired
    private BlogPostService blogPostService;

    @PostMapping("/credentials/upload-file/{blogId}")
    public ResponseEntity<String> uploadFile(@RequestParam("file") MultipartFile coverImage, @PathVariable("blogId") String blogId) throws Exception {
        String fileName = imageCrudService.uploadFile(coverImage);
        blogPostService.updateBlogCoverImageName(fileName, Long.parseLong(blogId));
        return ResponseEntity.ok().body(fileName);
    }

    @GetMapping("/credentials/view-file/{file-name}")
    public ResponseEntity<byte[]> viewFile(@PathVariable("file-name") String fileName) throws Exception{
        byte[] imageByteStream = imageCrudService.downloadFile(fileName);
        imageCrudService.deleteFile(fileName);
        return ResponseEntity.ok()
                .contentLength(imageByteStream.length)
                .header("Content-Type","image/jpeg")
                .header(HttpHeaders.CONTENT_DISPOSITION, "inline; filename=\"" + fileName + "\"")
                .body(imageByteStream);
        }
}
