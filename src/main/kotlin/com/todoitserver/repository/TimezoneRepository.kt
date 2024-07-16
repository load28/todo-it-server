package com.todoitserver.repository

import org.socialsignin.spring.data.dynamodb.repository.DynamoDBCrudRepository
import org.socialsignin.spring.data.dynamodb.repository.EnableScan

@EnableScan
interface TimezoneRepository : DynamoDBCrudRepository<String, String> {
}