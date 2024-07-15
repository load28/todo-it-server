package com.todoitserver.model

import com.amazonaws.services.dynamodbv2.datamodeling.*
import java.time.ZonedDateTime
import java.util.UUID.randomUUID

@DynamoDBTable(tableName = "todos")
data class Todo(
    @DynamoDBHashKey
    @DynamoDBAttribute
    var id: String = randomUUID().toString(),

    @DynamoDBAttribute
    var content: String,

    @DynamoDBAttribute
    var isCompleted: Boolean,

    @DynamoDBAttribute
    @DynamoDBTypeConverted(converter = ZonedDateTimeConverter::class)
    var date: ZonedDateTime? = null
) {
    constructor() : this(
        id = randomUUID().toString(),
        content = "",
        isCompleted = false,
        date = ZonedDateTime.now()
    )
}

class ZonedDateTimeConverter : DynamoDBTypeConverter<String, ZonedDateTime> {
    override fun convert(date: ZonedDateTime): String {
        return date.toString()
    }

    override fun unconvert(date: String): ZonedDateTime {
        return ZonedDateTime.parse(date)
    }
}