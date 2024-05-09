package com.Aksharam.GuideApp.guide;

import org.apache.commons.lang3.RandomStringUtils;
import org.springframework.stereotype.Service;

@Service
public class AlphaNumeric {
    Integer length = 12;
    public String generateRandomNumber() {
        //int length =10;
        return RandomStringUtils.randomAlphanumeric(length);
    }
}
