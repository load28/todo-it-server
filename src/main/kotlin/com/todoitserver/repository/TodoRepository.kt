package com.todoitserver.repository

import com.todoitserver.model.Todo
import org.socialsignin.spring.data.dynamodb.repository.DynamoDBCrudRepository
import org.socialsignin.spring.data.dynamodb.repository.EnableScan

@EnableScan
interface TodoRepository : DynamoDBCrudRepository<Todo, String> {
    fun findByDate(date: Long): List<Todo>
}
