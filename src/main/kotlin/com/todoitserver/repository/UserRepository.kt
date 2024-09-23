package com.todoitserver.repository

import com.todoitserver.model.User
import org.socialsignin.spring.data.dynamodb.repository.DynamoDBCrudRepository
import org.socialsignin.spring.data.dynamodb.repository.EnableScan

@EnableScan
interface UserRepository : DynamoDBCrudRepository<User, String> {
}
