package com.example.yourgeekengineer.services.s3;

import org.springframework.web.multipart.MultipartFile;

public interface FileCrudService {

    String uploadFile(MultipartFile file) throws Exception;
    Object downloadFile();

    Boolean deleteFile();
}
