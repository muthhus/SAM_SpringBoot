package com.sample.aws.s3.lambda.service.config;

import com.sample.aws.s3.lambda.service.core.service.UTubeGalleryService;
import com.sample.aws.s3.lambda.service.utils.aws.AWSLambdaFunctionMapHandler;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.context.support.PropertySourcesPlaceholderConfigurer;

@Configuration
@ComponentScan(basePackages = {"com.sample.aws.s3.lambda.service.core.service"})
@PropertySource("classpath:application.properties")
public class uTubeGalleryConfig {

    @Bean
    public AWSLambdaFunctionMapHandler getUTubeGalleryHandler(UTubeGalleryService uTubeGalleryService){
        return new AWSLambdaFunctionMapHandler(uTubeGalleryService);
    }

    @Bean
    public static PropertySourcesPlaceholderConfigurer propertySourcesPlaceholderConfigurer(){
        return new PropertySourcesPlaceholderConfigurer();
    }

}
