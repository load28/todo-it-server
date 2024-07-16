package com.todoitserver.config

import com.amazonaws.services.dynamodbv2.AmazonDynamoDB
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBMapper
import com.amazonaws.services.dynamodbv2.model.ProvisionedThroughput
import org.springframework.context.annotation.Configuration

@Configuration
class DynamoDBInitializer(
    private val dynamoDBMapper: DynamoDBMapper,
    private val amazonDynamoDB: AmazonDynamoDB
) {

    fun createTableIfNotExists(entityClass: Class<*>) {
        val tableRequest = dynamoDBMapper.generateCreateTableRequest(entityClass)
        tableRequest.provisionedThroughput = ProvisionedThroughput(1L, 1L)

        val tableName = tableRequest.tableName
        if (!amazonDynamoDB.listTables().tableNames.contains(tableName)) {
            amazonDynamoDB.createTable(tableRequest)
            println("Table $tableName created")
        } else {
            println("Table $tableName already exists")
        }
    }
}