package ru.oleynik.otus.api.gateway

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.context.properties.EnableConfigurationProperties
import org.springframework.boot.runApplication
import org.springframework.cloud.openfeign.EnableFeignClients
import ru.oleynik.otus.api.gateway.config.JwtConfig

@SpringBootApplication
@EnableFeignClients
@EnableConfigurationProperties(
    JwtConfig::class,
)
class ApiGatewayApplication

fun main(args: Array<String>) {
    runApplication<ApiGatewayApplication>(*args)
}
