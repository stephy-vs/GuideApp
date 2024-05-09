package com.Aksharam.GuideApp.file;

import com.amazonaws.services.s3.AmazonS3;
import com.amazonaws.services.s3.model.PutObjectRequest;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.FileOutputStream;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

@Service
@Slf4j
public class StorageService {
    @Value("${aws.s3.bucketName}")
    private String bucketName;

    @Autowired
    private AmazonS3 s3Client;

    @Autowired
    private StorageRepo storageRepo;

    public String uploadData(MultipartFile file, String name) {
        File fileObj = convertMultiPartFileToFile(file);
        String fileName =System.currentTimeMillis()+"_"+file.getOriginalFilename();
        s3Client.putObject(new PutObjectRequest(bucketName,fileName,fileObj));
        fileObj.delete();
        String fileUrl = s3Client.getUrl(bucketName,fileName).toString();
        StorageModel storageModel1 = new StorageModel();
        storageModel1.setFileName(fileName);
        storageModel1.setUrl(fileUrl);

        storageModel1.setName(name);
        storageRepo.save(storageModel1);
        return "File Name : "+fileName+";"+" URL: "+fileUrl;
    }

    //Upload file
    public String uploadFile(MultipartFile file){
        File fileObj = convertMultiPartFileToFile(file);
        String fileName =System.currentTimeMillis()+"_"+file.getOriginalFilename();
        s3Client.putObject(new PutObjectRequest(bucketName,fileName,fileObj));
        fileObj.delete();
        String fileUrl = s3Client.getUrl(bucketName,fileName).toString();
        StorageModel smodel = new StorageModel();
        smodel.setFileName(fileName);
        smodel.setUrl(fileUrl);
        Random r = new Random();
        Integer autoId = r.nextInt(9000) + 1000;
        smodel.setQrId(autoId);

        storageRepo.save(smodel);
        return "File Name : "+fileName+";"+" URL: "+fileUrl;
    }
    private File convertMultiPartFileToFile(MultipartFile file){
        File convertedFile = new File(file.getOriginalFilename());
        try(FileOutputStream fos = new FileOutputStream(convertedFile)) {
            fos.write(file.getBytes());

        }catch (Exception e){
            log.error("Error converting multipartFile to file",e);
        }
        return convertedFile;
    }

    public ResponseEntity<List<StorageModel>> getAllUrl() {
        try {
            return new ResponseEntity<>(storageRepo.findAll(), HttpStatus.OK);
        }catch (Exception e){
            e.printStackTrace();
        }
        return new ResponseEntity<>(new ArrayList<>(),HttpStatus.BAD_REQUEST);
    }
}
