package com.todoitserver.service

import com.todoitserver.repository.TimezoneRepository
import org.springframework.stereotype.Service

@Service
class TimezoneService(private val timezoneRepository: TimezoneRepository) {
    fun getTimezone(userId: String): String {
        return timezoneRepository.findById(userId).orElseThrow { IllegalArgumentException("User not found") }
    }

    fun updateTimezone(userId: String, timezone: String): String {
        val userTimezone =
            timezoneRepository.findById(userId).orElseThrow { IllegalArgumentException("User not found") }
        timezoneRepository.save(timezone)
        return timezone
    }
}