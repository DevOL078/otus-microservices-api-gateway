package ru.oleynik.otus.api.gateway.out

import org.springframework.cloud.openfeign.FeignClient
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestParam
import ru.oleynik.otus.api.gateway.config.FeignConfig
import ru.oleynik.otus.api.gateway.out.dto.UserRequest
import ru.oleynik.otus.api.gateway.out.dto.UserResponse

@FeignClient(
    value = "user-service",
    url = "\${user-service.url}",
    configuration = [FeignConfig::class])
interface UserServiceClient {

    @PostMapping
    fun create(request: UserRequest): UserResponse

    @GetMapping("/search")
    fun getByLoginAndPassword(
        @RequestParam("login") login: String,
        @RequestParam("password") password: String,
    ): List<UserResponse>

}