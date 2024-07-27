package ru.oleynik.otus.api.gateway.`in`.rest

import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController
import ru.oleynik.otus.api.gateway.exception.UserExistsException
import ru.oleynik.otus.api.gateway.exception.UserNotFoundException
import ru.oleynik.otus.api.gateway.`in`.rest.dto.AuthResponse
import ru.oleynik.otus.api.gateway.`in`.rest.dto.LoginRequest
import ru.oleynik.otus.api.gateway.`in`.rest.dto.RegisterRequest
import ru.oleynik.otus.api.gateway.usecase.LoginUseCase
import ru.oleynik.otus.api.gateway.usecase.RegisterUseCase

@RestController
@RequestMapping("/auth")
class AuthController(
    private val registerUseCase: RegisterUseCase,
    private val loginUseCase: LoginUseCase,
) {

    @PostMapping("/register")
    fun register(@RequestBody request: RegisterRequest): ResponseEntity<AuthResponse> {
        return try {
            ResponseEntity.ok(registerUseCase.exec(request))
        } catch (e: UserExistsException) {
            ResponseEntity.status(HttpStatus.CONFLICT).build()
        }
    }

    @PostMapping("/login")
    fun login(@RequestBody request: LoginRequest): ResponseEntity<AuthResponse> {
        return try {
            ResponseEntity.ok(loginUseCase.exec(request))
        } catch (e: UserNotFoundException) {
            ResponseEntity.status(HttpStatus.NOT_FOUND).build()
        }
    }

}