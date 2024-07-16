package com.todoitserver.controller

import com.todoitserver.dto.TimezoneRequestDTO
import com.todoitserver.model.Timezone
import com.todoitserver.service.TimezoneService
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping("/timezone")
class TimezoneController(private val timezoneService: TimezoneService) {

    @GetMapping("/get")
    fun getTimezone(@RequestParam userId: String): ResponseEntity<Timezone> {
        return ResponseEntity(timezoneService.getTimezone(userId), HttpStatus.OK)
    }

    @PostMapping("/update")
    fun updateTimezone(@RequestBody data: TimezoneRequestDTO): ResponseEntity<Timezone> {
        return ResponseEntity(
            timezoneService.updateTimezone(Timezone(userId = data.userId, timezone = data.timezone)),
            HttpStatus.OK
        )
    }
}