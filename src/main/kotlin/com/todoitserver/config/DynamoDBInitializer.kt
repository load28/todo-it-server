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
        val todoTableRequest = createTodoTableReqest()
        val tzTableRequest = createTimezoneTableRequest()

        try {
            amazonDynamoDB.createTable(todoTableRequest)
            amazonDynamoDB.createTable(tzTableRequest)
        } catch (e: ResourceInUseException) {
            println("Table already exists")
        }
    }

    fun createTimezoneTableRequest(): CreateTableRequest {
        val tableRequest = dynamoDBMapper.generateCreateTableRequest(Timezone::class.java)
        tableRequest.provisionedThroughput = ProvisionedThroughput(5L, 5L)

        return tableRequest
    }

    fun createTodoTableReqest(): CreateTableRequest {
        val tableRequest = dynamoDBMapper.generateCreateTableRequest(Todo::class.java)
        tableRequest.provisionedThroughput = ProvisionedThroughput(5L, 5L)

        val dateGSI = GlobalSecondaryIndex()
            .withIndexName("dateIndex")
            .withKeySchema(KeySchemaElement("date", KeyType.HASH))
            .withProjection(Projection().withProjectionType(ProjectionType.ALL))
            .withProvisionedThroughput(ProvisionedThroughput(5L, 5L))
        
        val userIdGSI = GlobalSecondaryIndex()
            .withIndexName("userIdIndex")
            .withKeySchema(KeySchemaElement("userId", KeyType.HASH))
            .withProjection(Projection().withProjectionType(ProjectionType.ALL))
            .withProvisionedThroughput(ProvisionedThroughput(5L, 5L))

        tableRequest.setGlobalSecondaryIndexes(listOf(dateGSI, userIdGSI))

        return tableRequest
    }
}
