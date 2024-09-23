package com.todoitserver.controller

import com.todoitserver.service.AuthService
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

data class GoogleLoginRequest(val token: String)
data class SuccessResponse<T>(val data: T)
data class ErrorResponse(val message: String)

@RestController
@RequestMapping("/auth")
class AuthController(private val authService: AuthService) {
    @PostMapping("google")
    fun googleLogin(@RequestBody request: GoogleLoginRequest): ResponseEntity<Any> {
        val userInfo = authService.verifyGoogleToken(request.token)
        if (userInfo != null) {
            val successResponse = SuccessResponse(userInfo)
            return ResponseEntity(successResponse, HttpStatus.OK)
        } else {
            val errorResponse = ErrorResponse("Not found user")
            return ResponseEntity(errorResponse, HttpStatus.UNAUTHORIZED)
        }
    }
}