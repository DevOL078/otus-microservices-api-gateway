package ru.oleynik.otus.api.gateway.usecase

import feign.FeignException
import org.springframework.http.HttpStatus
import org.springframework.stereotype.Component
import ru.oleynik.otus.api.gateway.exception.UserNotFoundException
import ru.oleynik.otus.api.gateway.`in`.rest.dto.AuthResponse
import ru.oleynik.otus.api.gateway.`in`.rest.dto.LoginRequest
import ru.oleynik.otus.api.gateway.jwt.JwtService
import ru.oleynik.otus.api.gateway.out.UserServiceClient

@Component
class LoginUseCase(
    private val userServiceClient: UserServiceClient,
    private val jwtService: JwtService,
) {
    fun exec(request: LoginRequest): AuthResponse {
        try {
            val user = userServiceClient.getByLoginAndPassword(request.username, request.password)
            return user.let {
                AuthResponse(it.id, jwtService.generateToken(request.username, it.id))
            }
        } catch (e: FeignException) {
            if (e.status() == HttpStatus.NOT_FOUND.value()) {
                throw UserNotFoundException("user with login ${request.username} was not found")
            } else {
                throw RuntimeException(e)
            }
        }
    }
}