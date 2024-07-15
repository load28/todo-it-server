package com.todoitserver

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication

@SpringBootApplication
class TodoItServerApplication

fun main(args: Array<String>) {
    runApplication<TodoItServerApplication>(*args)
}
