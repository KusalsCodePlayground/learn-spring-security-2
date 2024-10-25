package com.example.test.util;

import org.springframework.stereotype.Component;

import java.util.UUID;


public class AppUtil {

    public static String generateUserId(){
        return "U-"+UUID.randomUUID();
    }
}
