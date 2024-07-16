package com.todoitserver.config

import com.amazonaws.services.dynamodbv2.AmazonDynamoDB
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBMapper
import com.amazonaws.services.dynamodbv2.model.CreateTableRequest
import com.amazonaws.services.dynamodbv2.model.ProvisionedThroughput
import com.todoitserver.model.Todo
import org.springframework.boot.CommandLineRunner
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration

@Configuration
class DynamoDBInitializer {

    @Bean
    fun initializerDynamoDB(awsDynamoDB: AmazonDynamoDB, dynamoDBMapper: DynamoDBMapper): CommandLineRunner {
        return CommandLineRunner {
            val tableRequest: CreateTableRequest = dynamoDBMapper.generateCreateTableRequest(Todo::class.java)
            tableRequest.provisionedThroughput = ProvisionedThroughput(1L, 1L)

            val tableNames = awsDynamoDB.listTables().tableNames
            if (!tableNames.contains("todo")) {
                awsDynamoDB.createTable(tableRequest)
            }
        }
    }
}