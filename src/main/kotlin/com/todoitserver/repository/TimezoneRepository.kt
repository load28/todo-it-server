package com.todoitserver.repository

import com.todoitserver.model.Timezone
import org.socialsignin.spring.data.dynamodb.repository.DynamoDBCrudRepository
import org.socialsignin.spring.data.dynamodb.repository.EnableScan

@EnableScan
interface TimezoneRepository : DynamoDBCrudRepository<Timezone, String> {
}