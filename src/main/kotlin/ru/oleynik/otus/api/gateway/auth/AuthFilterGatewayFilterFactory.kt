package ru.oleynik.otus.api.gateway.auth

import org.springframework.cloud.gateway.filter.GatewayFilter
import org.springframework.cloud.gateway.filter.factory.AbstractGatewayFilterFactory
import org.springframework.stereotype.Component

@Component
class AuthFilterGatewayFilterFactory(
    private val authFilter: AuthFilter,
) : AbstractGatewayFilterFactory<Any>() {
    override fun apply(config: Any): GatewayFilter {
        return authFilter
    }
}