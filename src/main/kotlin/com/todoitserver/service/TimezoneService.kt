package com.todoitserver.service

import com.todoitserver.model.Timezone
import com.todoitserver.repository.TimezoneRepository
import org.springframework.stereotype.Service

@Service
class TimezoneService(private val timezoneRepository: TimezoneRepository) {
    fun getTimezone(userId: String): Timezone {
        return timezoneRepository.findById(userId).orElseThrow { IllegalArgumentException("User not found") }
    }

    fun updateTimezone(timezone: Timezone): Timezone {
        timezoneRepository.findById(timezone.userId).orElseThrow { IllegalArgumentException("User not found") }
        timezoneRepository.save(timezone)
        return timezone
    }
}