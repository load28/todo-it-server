package com.todoitserver.service

import com.todoitserver.dto.ActionType
import com.todoitserver.dto.TodoUpdateRequestDTO
import com.todoitserver.model.Todo
import com.todoitserver.repository.TodoRepository
import org.springframework.stereotype.Service
import java.time.ZonedDateTime

@Service
class TodoService(private val todoRepository: TodoRepository) {

    fun getTodosByDate(date: ZonedDateTime): List<Todo> {
        return try {
            todoRepository.findByDate(date)
        } catch (e: Exception) {
            when (e) {
                is NoSuchElementException -> emptyList<Todo>()
                is IllegalArgumentException -> throw IllegalArgumentException("Invalid date format")
                else -> throw e
            }
        }
    }

    fun createTodos(todos: List<Todo>): List<Todo> {
        todos.forEach { validateTodo(it.content, it.date) }
        return todoRepository.saveAll(todos).toList()
    }

    fun updateTodos(data: TodoUpdateRequestDTO): List<Todo> {
        val updatedTodos = mutableListOf<Todo>()
        val date = data.date
        val todos = data.todos

        for (updateTodo in todos) {
            validateTodo(updateTodo.content, date)
        }

        for (updateTodo in todos) {
            when (updateTodo.action) {
                ActionType.ADD -> {
                    val newTodo = Todo(
                        content = updateTodo.content,
                        isCompleted = updateTodo.isCompleted,
                        date = date
                    )
                    todoRepository.save(newTodo)
                    updatedTodos.add(newTodo)
                }

                ActionType.UPDATE -> {
                    updateTodo.id.let { id ->
                        if (id != null) {
                            val todo =
                                todoRepository.findById(id)
                                    .orElseThrow { IllegalArgumentException("Todo not found") }
                            todo.content = updateTodo.content
                            todo.isCompleted = updateTodo.isCompleted
                            todoRepository.save(todo)
                            updatedTodos.add(todo)
                        }
                    }
                }

                ActionType.DELETE -> {
                    updateTodo.id.let { id ->
                        if (id != null) {
                            try {
                                todoRepository.findById(id)
                                    .orElseThrow { IllegalArgumentException("Todo not found") }
                                todoRepository.deleteById(id)
                            } catch (e: Exception) {
                                when (e) {
                                    is IllegalArgumentException -> throw IllegalArgumentException("Todo not found")
                                    else -> throw e
                                }
                            }
                        }
                    }
                }
            }
        }

        return updatedTodos
    }


    fun deleteTodoByDate(date: ZonedDateTime) {
        val todos = todoRepository.findByDate(date)
        todoRepository.deleteAll(todos)
    }

    private fun validateTodo(content: String?, date: ZonedDateTime?) {
        if (content.isNullOrEmpty()) {
            throw IllegalArgumentException("Content cannot be blank")
        }

        if (date == null) {
            throw IllegalArgumentException("Date cannot be null")
        }
    }
}