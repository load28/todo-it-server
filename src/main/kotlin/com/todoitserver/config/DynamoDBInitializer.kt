package com.todoitserver.config

import com.amazonaws.services.dynamodbv2.AmazonDynamoDB
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBMapper
import com.amazonaws.services.dynamodbv2.model.*
import com.todoitserver.model.Timezone
import com.todoitserver.model.Todo
import jakarta.annotation.PostConstruct
import org.springframework.context.annotation.Configuration

@Configuration
class DynamoDBInitializer(
    private val dynamoDBMapper: DynamoDBMapper,
    private val amazonDynamoDB: AmazonDynamoDB
) {

    @PostConstruct
    fun initialize() {
        val todoTableRequest = createTableRequestWithGIS(Todo::class.java, "date", "dateIndex")
        val tzTableRequest = createTableRequest(Timezone::class.java)

        try {
            amazonDynamoDB.createTable(todoTableRequest)
            amazonDynamoDB.createTable(tzTableRequest)
        } catch (e: ResourceInUseException) {
            println("Table already exists")
        }
    }

    fun createTableRequest(entityClass: Class<*>): CreateTableRequest {
        val tableRequest = dynamoDBMapper.generateCreateTableRequest(entityClass)
        tableRequest.provisionedThroughput = ProvisionedThroughput(1L, 1L)
        return tableRequest
    }

    fun createTableRequestWithGIS(entityClass: Class<*>, key: String, index: String): CreateTableRequest {
        val tableRequest = dynamoDBMapper.generateCreateTableRequest(entityClass)
        tableRequest.provisionedThroughput = ProvisionedThroughput(5L, 5L)

        val gsi = GlobalSecondaryIndex()
            .withIndexName(index)
            .withKeySchema(KeySchemaElement(key, KeyType.HASH))
            .withProjection(Projection().withProjectionType(ProjectionType.ALL))
            .withProvisionedThroughput(ProvisionedThroughput(5L, 5L))

        tableRequest.setGlobalSecondaryIndexes(listOf(gsi))

        return tableRequest
    }
}
