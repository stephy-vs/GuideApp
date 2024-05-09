package com.Aksharam.GuideApp.file;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@RestController
@RequestMapping(path = "/fileData")
@CrossOrigin
public class StorageController {
    @Autowired
    private StorageService storageService;

    @PostMapping(path = "/uploadJPG")
    public ResponseEntity<String> uploadFile(@RequestParam(value = "file") MultipartFile file){
        return new ResponseEntity<>(storageService.uploadFile(file), HttpStatus.OK);
    }


    @PostMapping(path = "/testData")
    public ResponseEntity<String> uploadData(@RequestParam(value = "file") MultipartFile file,
                                             @RequestPart("data") String name){
        return new ResponseEntity<>(storageService.uploadData(file,name),HttpStatus.OK);
    }

    @GetMapping(path = "/getAllUrl")
    public ResponseEntity<List<StorageModel>> getAllUrl(){

        return storageService.getAllUrl();
    }
}
