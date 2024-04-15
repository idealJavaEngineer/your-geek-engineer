package com.example.yourgeekengineer.services.s3;

import com.amazonaws.services.s3.AmazonS3;
import com.amazonaws.services.s3.model.ObjectMetadata;
import com.amazonaws.services.s3.model.PutObjectRequest;
import org.apache.commons.io.FilenameUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.FileOutputStream;
import java.util.Objects;

@Service
public class ImageCrudService implements  FileCrudService {

    @Value("${aws.bucket.name}")
    private String bucketName;

    @Autowired
    private AmazonS3 s3Client;

    @Override
    public String uploadFile(MultipartFile multipartFile) throws Exception {
        File file = new File(Objects.requireNonNull(multipartFile.getOriginalFilename()));
        try(FileOutputStream fileOutputStream = new FileOutputStream(file);) {
           fileOutputStream.write(multipartFile.getBytes());
            //upload file
            String fileName = generateFileName(multipartFile);
            PutObjectRequest request = new PutObjectRequest(bucketName, fileName, file);
            ObjectMetadata metadata = new ObjectMetadata();
            metadata.setContentType("plain/"+ FilenameUtils.getExtension(multipartFile.getOriginalFilename()));
            metadata.addUserMetadata("Title", "File Upload - " + fileName);
            metadata.setContentLength(file.length());
            request.setMetadata(metadata);
            s3Client.putObject(request);
            return fileName;
        } catch (Exception exception) {
            throw new Exception(exception.getMessage());
        } finally {
            file.delete();
        }
    }

    @Override
    public Object downloadFile() {
        return null;
    }

    @Override
    public Boolean deleteFile() {
        return null;
    }

    private String generateFileName(MultipartFile file) {
        String timestamp = String.valueOf(System.currentTimeMillis());
        return  timestamp + "_" + file.getOriginalFilename().replace(" ", "_");
    }
}
