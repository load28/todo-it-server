package com.todoitserver.controller

import com.todoitserver.dto.TodoUpdateRequestDTO
import com.todoitserver.model.Todo
import com.todoitserver.service.TodoService
import org.springframework.format.annotation.DateTimeFormat
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RequestParam
import org.springframework.web.bind.annotation.RestController
import java.time.ZonedDateTime

@RestController
@RequestMapping("/todos")
class TodoController(private val todoService: TodoService) {

    @RequestMapping("/create")
    fun createTodos(@RequestBody todos: List<Todo>): ResponseEntity<List<Todo>> {
        return ResponseEntity(todoService.createTodos(todos), HttpStatus.CREATED)
    }

    @RequestMapping("/update")
    fun updateTodos(@RequestBody data: TodoUpdateRequestDTO): ResponseEntity<List<Todo>> {
        return ResponseEntity(todoService.updateTodos(data), HttpStatus.OK)
    }

    @RequestMapping("/get")
    fun getTodosByDate(@RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME) date: ZonedDateTime): ResponseEntity<List<Todo>> {
        println(date)
        return ResponseEntity(todoService.getTodosByDate(date), HttpStatus.OK)
    }

    @RequestMapping("/delete")
    fun deleteTodoByDate(@RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME) date: ZonedDateTime): ResponseEntity<Unit> {
        todoService.deleteTodoByDate(date)
        return ResponseEntity(HttpStatus.NO_CONTENT)
    }
}