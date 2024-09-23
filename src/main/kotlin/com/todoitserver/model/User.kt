package com.todoitserver.model

import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBAttribute
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBHashKey
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBTable
import java.util.UUID.randomUUID

@DynamoDBTable(tableName = "user")
data class User(
    @DynamoDBHashKey
    @DynamoDBAttribute
    var id: String = randomUUID().toString(),

    @DynamoDBAttribute
    var email: String,

    @DynamoDBAttribute
    var name: String,

    @DynamoDBAttribute
    var pictureUrl: String?,
) {
    constructor() : this(id = randomUUID().toString(), email = "", name = "", pictureUrl = "")
}
