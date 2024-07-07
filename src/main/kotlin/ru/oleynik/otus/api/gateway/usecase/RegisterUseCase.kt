package ru.oleynik.otus.api.gateway.usecase

import org.springframework.stereotype.Component
import ru.oleynik.otus.api.gateway.`in`.rest.dto.AuthResponse
import ru.oleynik.otus.api.gateway.`in`.rest.dto.RegisterRequest
import ru.oleynik.otus.api.gateway.`in`.rest.dto.toUserRequest
import ru.oleynik.otus.api.gateway.out.UserServiceClient

@Component
class RegisterUseCase(
    private val userServiceClient: UserServiceClient,
) {

    fun exec(request: RegisterRequest): AuthResponse {
        return userServiceClient.create(request.toUserRequest())
            .let {
                AuthResponse(it.id, "token")
            }
    }

}