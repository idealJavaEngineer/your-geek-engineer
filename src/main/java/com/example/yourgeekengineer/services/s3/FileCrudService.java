package com.example.yourgeekengineer.services.s3;

import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

public interface FileCrudService {

    String uploadFile(MultipartFile file) throws Exception;
    Object downloadFile(String fileName) throws Exception;

    Boolean deleteFile(String fileName) throws IOException;
}
