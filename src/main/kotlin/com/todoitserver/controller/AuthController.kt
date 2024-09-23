package com.todoitserver.controller

import com.todoitserver.service.AuthService
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

data class GoogleLoginRequest(val token: String)

@RestController
@RequestMapping("/auth")
class AuthController(private val authService: AuthService) {
    @PostMapping("google")
    fun googleLogin(@RequestBody request: GoogleLoginRequest): ResponseEntity<Any> {
        val userInfo = authService.verifyGoogleToken(request.token)
        if (userInfo != null) {
            return ResponseEntity.ok(userInfo)
        } else {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Not found user")
        }
    }
}