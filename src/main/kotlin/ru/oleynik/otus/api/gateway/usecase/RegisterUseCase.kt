package ru.oleynik.otus.api.gateway.usecase

import org.springframework.stereotype.Component
import ru.oleynik.otus.api.gateway.`in`.rest.dto.AuthResponse
import ru.oleynik.otus.api.gateway.`in`.rest.dto.RegisterRequest
import ru.oleynik.otus.api.gateway.`in`.rest.dto.toUserRequest
import ru.oleynik.otus.api.gateway.jwt.JwtService
import ru.oleynik.otus.api.gateway.out.UserServiceClient

@Component
class RegisterUseCase(
    private val userServiceClient: UserServiceClient,
    private val jwtService: JwtService,
) {

    fun exec(request: RegisterRequest): AuthResponse {
        val userId = userServiceClient.create(request.toUserRequest()).id
        return AuthResponse(
            userId,
            jwtService.generateToken(request.username, userId))
    }

}