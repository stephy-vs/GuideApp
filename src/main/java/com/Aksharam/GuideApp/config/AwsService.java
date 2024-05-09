package com.Aksharam.GuideApp.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;


public class AwsService {
    @Autowired
    private AwsProperties awsProperties;

    public void setValuesOfAws(){
        String accessKey = awsProperties.getAccessKey();
        String secretKey = awsProperties.getSecretKey();
        String region = awsProperties.getRegion();
        String bucketName = awsProperties.getS3BucketName();

    }
}
