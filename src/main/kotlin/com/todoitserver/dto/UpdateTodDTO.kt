package com.todoitserver.dto

import java.time.ZonedDateTime

data class TodoUpdateDto(
    val id: String,
    val content: String,
    val isCompleted: Boolean,
    val action: ActionType
)

enum class ActionType {
    ADD, UPDATE, DELETE
}

data class TodoUpdateRequestDTO(
    val todos: List<TodoUpdateDto>,
    val date: ZonedDateTime
)