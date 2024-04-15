package com.example.yourgeekengineer.models;

import lombok.Getter;
import lombok.Setter;
import org.springframework.web.multipart.MultipartFile;

@Getter
@Setter
public class UploadBlogCoverImage {

    private MultipartFile multipartFile;
    private long blogId;

    public UploadBlogCoverImage(MultipartFile multipartFile, long blogId) {
        this.multipartFile = multipartFile;
        this.blogId = blogId;
    }
}
