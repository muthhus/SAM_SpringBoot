package com.sample.aws.s3.lambda.service.utils.aws.auth;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;

import java.nio.ByteBuffer;

import com.amazonaws.client.builder.AwsClientBuilder;
import com.amazonaws.services.secretsmanager.AWSSecretsManager;
import com.amazonaws.services.secretsmanager.AWSSecretsManagerClientBuilder;
import com.amazonaws.services.secretsmanager.model.GetSecretValueRequest;
import com.amazonaws.services.secretsmanager.model.GetSecretValueResult;
import com.amazonaws.services.secretsmanager.model.ResourceNotFoundException;
import com.amazonaws.services.secretsmanager.model.InvalidRequestException;
import com.amazonaws.services.secretsmanager.model.InvalidParameterException;

public class AwsAuthHandler {

    private static Logger _logger = LoggerFactory.getLogger(AwsAuthHandler.class);

    private static String endPoint;
    private static String region;
    private static String secretName;

    @Value("${aws.auth.endPoint:OVERRIDE_ME}")
    private void setEndPoint(String endPoint){
        this.endPoint = endPoint;
    }

    @Value("${aws.auth.region:OVERRIDE_ME}")
    private void setRegion(String region){
        this.region = region;
    }

    @Value("${aws.auth.secretName:OVERRIDE_ME}")
    private void setSecretName(String secretName){
        this.secretName = secretName;
    }

    public static String getSecret() {
        AwsClientBuilder.EndpointConfiguration config = new AwsClientBuilder.EndpointConfiguration(endPoint, region);
        AWSSecretsManagerClientBuilder clientBuilder = AWSSecretsManagerClientBuilder.standard();
        clientBuilder.setEndpointConfiguration(config);
        AWSSecretsManager client = clientBuilder.build();
        String secret = null;
        ByteBuffer binarySecretData;
        GetSecretValueRequest getSecretValueRequest = new GetSecretValueRequest().withSecretId(secretName);
        GetSecretValueResult getSecretValueResponse = null;
        try {
            getSecretValueResponse = client.getSecretValue(getSecretValueRequest);

        } catch(ResourceNotFoundException e) {
            _logger.info("The requested secret " + secretName + " was not found");
        } catch (InvalidRequestException e) {
            _logger.info("The request was invalid due to: ", e.getMessage());
        } catch (InvalidParameterException e) {
            _logger.info("The request had invalid params: " , e.getMessage());
        }

        if(getSecretValueResponse == null) {
            return null;
        }

        if(getSecretValueResponse.getSecretString() != null) {
            secret = getSecretValueResponse.getSecretString();
        }
        else {
            binarySecretData = getSecretValueResponse.getSecretBinary();
        }

        return secret;
    }
}
