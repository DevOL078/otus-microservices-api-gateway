package ru.oleynik.otus.api.gateway.usecase

import org.springframework.stereotype.Component
import ru.oleynik.otus.api.gateway.`in`.rest.dto.AuthResponse
import ru.oleynik.otus.api.gateway.`in`.rest.dto.LoginRequest
import ru.oleynik.otus.api.gateway.out.UserServiceClient

@Component
class LoginUseCase(
    private val userServiceClient: UserServiceClient,
) {
    fun exec(request: LoginRequest): AuthResponse {
        val users = userServiceClient.getByLoginAndPassword(request.username, request.password)
        return users.firstOrNull()?.let {
            AuthResponse(it.id, "token")
        } ?: throw IllegalStateException("login or password is invalid")
    }
}