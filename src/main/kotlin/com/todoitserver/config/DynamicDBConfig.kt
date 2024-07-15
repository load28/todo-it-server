package com.todoitserver.config

import com.amazonaws.auth.AWSStaticCredentialsProvider
import com.amazonaws.auth.BasicAWSCredentials
import com.amazonaws.client.builder.AwsClientBuilder
import com.amazonaws.services.dynamodbv2.AmazonDynamoDB
import com.amazonaws.services.dynamodbv2.AmazonDynamoDBClientBuilder
import org.socialsignin.spring.data.dynamodb.repository.config.EnableDynamoDBRepositories
import org.springframework.beans.factory.annotation.Value
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.context.annotation.Profile

@Profile("!local")
@Configuration
@EnableDynamoDBRepositories(basePackages = ["com.todoitserver.repository"])
class DynamicDBConfig {

    @Value("\${amazon.dynamodb.endpoint}")
    private lateinit var amazonDynamoDBEndpoint: String

    @Value("\${amazon.aws.accesskey}")
    private lateinit var amazoneAWSAcesskey: String

    @Value("\${amazon.aws.secretkey}")
    private lateinit var amazonAWSSecretkey: String

    @Bean
    fun amazonDynamoDB(): AmazonDynamoDB {
        return AmazonDynamoDBClientBuilder.standard()
            .withEndpointConfiguration(AwsClientBuilder.EndpointConfiguration(amazonDynamoDBEndpoint, "us-west-2"))
            .withCredentials(AWSStaticCredentialsProvider(BasicAWSCredentials(amazoneAWSAcesskey, amazonAWSSecretkey)))
            .build()
    }
}