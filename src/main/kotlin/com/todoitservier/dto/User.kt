package com.todoitservier.dto

import jakarta.persistence.Entity
import jakarta.persistence.GeneratedValue
import jakarta.persistence.Id

@Entity
data class Todo(
    @Id @GeneratedValue
    val id: Long = 0,
    var title: String,
    var completed: Boolean = false
)