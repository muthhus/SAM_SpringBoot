package com.sample.aws.s3.lambda.service.utils.aws;

import com.amazonaws.services.lambda.runtime.Context;
import com.amazonaws.services.lambda.runtime.RequestHandler;
import com.sample.aws.s3.lambda.service.core.service.UTubeGalleryService;
import lombok.NoArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import java.util.Map;

@Component
@NoArgsConstructor
public class AWSLambdaFunctionMapHandler implements RequestHandler<Map<String, Object>, String> {

    private static Logger _logger = LoggerFactory.getLogger(AWSLambdaFunctionMapHandler.class);

    private UTubeGalleryService uTubeGalleryService;

    public AWSLambdaFunctionMapHandler(UTubeGalleryService uTubeGalleryService){
        this.uTubeGalleryService = uTubeGalleryService;
    }

    @Override
    public String handleRequest(Map<String, Object> stringObjectMap, Context context) {
        return null;
    }
}
