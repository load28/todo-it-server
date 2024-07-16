package com.todoitserver.model

import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBAttribute
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBHashKey
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBTable

@DynamoDBTable(tableName = "timezone")
data class Timezone(
    @DynamoDBHashKey
    @DynamoDBAttribute
    var userId: String,

    @DynamoDBAttribute
    var timezone: String,
)