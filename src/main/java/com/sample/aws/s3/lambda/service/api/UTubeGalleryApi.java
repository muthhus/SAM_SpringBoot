package com.sample.aws.s3.lambda.service.api;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api")
public class UTubeGalleryApi {

    @RequestMapping("/")
    public String getHomePage(){
        return "Welcome";
    }
}
