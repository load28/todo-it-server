package com.todoitserver.dto

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

data class TodoUpdateRequestDTO(
    val todos: List<TodoUpdateDto>,
    val date: Long
)
