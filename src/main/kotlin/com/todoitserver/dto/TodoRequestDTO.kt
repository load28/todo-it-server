package com.todoitserver.dto

import com.todoitserver.model.Todo

data class TodoUpdateDto(
    val id: String?,
    val content: String,
    val isCompleted: Boolean,
    val action: ActionType,
    val userId: String,
)

enum class ActionType {
    ADD {
        val symbol: String = "ADD"
    },
    UPDATE {
        val symbol: String = "UPDATE"
    },
    DELETE {
        val symbol: String = "DELETE"
    }
}

data class TodoCreateRequestDTO(
    val todos: List<Todo>,
    val userId: String
)

data class TodoUpdateRequestDTO(
    val todos: List<TodoUpdateDto>,
    val date: Long
)
