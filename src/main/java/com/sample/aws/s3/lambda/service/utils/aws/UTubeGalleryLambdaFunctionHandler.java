package com.sample.aws.s3.lambda.service.utils.aws;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

import com.amazonaws.serverless.exceptions.ContainerInitializationException;
import com.amazonaws.serverless.proxy.model.AwsProxyRequest;
import com.amazonaws.serverless.proxy.model.AwsProxyResponse;
import com.amazonaws.serverless.proxy.spring.SpringBootLambdaContainerHandler;
import com.amazonaws.services.lambda.runtime.Context;
import com.amazonaws.services.lambda.runtime.RequestStreamHandler;
import com.sample.aws.s3.lambda.service.AwsLambdaAndS3Application;

public class UTubeGalleryLambdaFunctionHandler implements RequestStreamHandler {

    private static SpringBootLambdaContainerHandler<AwsProxyRequest, AwsProxyResponse> handler;

    static {
        try {
            handler = SpringBootLambdaContainerHandler.getAwsProxyHandler(AwsLambdaAndS3Application.class);
            handler.activateSpringProfiles("lambda");
        } catch (ContainerInitializationException e) {
            throw new RuntimeException("Failed to initialize the Application", e);
        }
    }

    @Override
    public void handleRequest(InputStream inputStream, OutputStream outputStream, Context context) throws IOException {
        handler.proxyStream(inputStream, outputStream, context);
        outputStream.close();
    }

}
