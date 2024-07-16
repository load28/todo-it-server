package com.todoitserver.controller

import com.todoitserver.dto.TimezoneRequestDTO
import com.todoitserver.service.TimezoneService
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping("/timezone")
class TimezoneController(private val timezoneService: TimezoneService) {

    @GetMapping("/get")
    fun getTimezone(@RequestParam userId: String): ResponseEntity<String> {
        return ResponseEntity(timezoneService.getTimezone(userId), HttpStatus.OK)
    }

    @PostMapping("/update")
    fun updateTimezone(@RequestBody data: TimezoneRequestDTO): ResponseEntity<String> {
        return ResponseEntity(timezoneService.updateTimezone(data.userId, data.timezone), HttpStatus.OK)
    }
}