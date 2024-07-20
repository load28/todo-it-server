package com.todoitserver.controller

import com.todoitserver.dto.TodoUpdateRequestDTO
import com.todoitserver.model.Todo
import com.todoitserver.service.TodoService
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping("/todo")
class TodoController(private val todoService: TodoService) {

    @PostMapping("/create")
    fun createTodos(@RequestBody todos: List<Todo>): ResponseEntity<List<Todo>> {
        return ResponseEntity(todoService.createTodos(todos), HttpStatus.CREATED)
    }

    @PostMapping("/update")
    fun updateTodos(@RequestBody data: TodoUpdateRequestDTO): ResponseEntity<List<Todo>> {
        return ResponseEntity(todoService.updateTodos(data), HttpStatus.OK)
    }

    @GetMapping("/get")
    fun getTodosByDate(@RequestParam userId: String): ResponseEntity<List<Todo>> {
        return ResponseEntity(todoService.getTodosByUserId(userId), HttpStatus.OK)
    }

    @PostMapping("/delete")
    fun deleteTodoByDate(@RequestBody date: Long): ResponseEntity<Unit> {
        todoService.deleteTodoByDate(date)
        return ResponseEntity(HttpStatus.NO_CONTENT)
    }
}
