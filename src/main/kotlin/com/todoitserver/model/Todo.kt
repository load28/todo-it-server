package com.todoitserver.model

import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBAttribute
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBHashKey
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBIndexHashKey
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBTable
import java.util.UUID.randomUUID

@DynamoDBTable(tableName = "todo")
data class Todo(
    @DynamoDBHashKey
    @DynamoDBAttribute
    var id: String = randomUUID().toString(),

    @DynamoDBAttribute
    var content: String,

    @DynamoDBAttribute
    var isCompleted: Boolean,

    @DynamoDBAttribute
    @DynamoDBIndexHashKey(globalSecondaryIndexName = "userIdIndex")
    var userId: String,

    @DynamoDBAttribute
    @DynamoDBIndexHashKey(globalSecondaryIndexName = "dateIndex")
    var date: Long
) {
    constructor() : this(id = randomUUID().toString(), content = "", isCompleted = false, userId = "", date = 0)
}
