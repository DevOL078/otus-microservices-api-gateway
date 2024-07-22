package ru.oleynik.otus.api.gateway.auth

import org.springframework.cloud.context.config.annotation.RefreshScope
import org.springframework.cloud.gateway.filter.GatewayFilter
import org.springframework.cloud.gateway.filter.GatewayFilterChain
import org.springframework.http.HttpStatus
import org.springframework.security.access.AccessDeniedException
import org.springframework.stereotype.Component
import org.springframework.web.server.ServerWebExchange
import reactor.core.publisher.Mono
import ru.oleynik.otus.api.gateway.jwt.JwtService

@RefreshScope
@Component
class AuthFilter(
    private val jwtService: JwtService,
) : GatewayFilter {
    override fun filter(exchange: ServerWebExchange?, chain: GatewayFilterChain?): Mono<Void> {
        val request = requireNotNull(exchange).request
        if (!request.headers.containsKey(AUTHORIZATION_HEADER)) {
            return exchange.response
                .also { it.statusCode = HttpStatus.UNAUTHORIZED }
                .setComplete()
        }

        val token = request.headers[AUTHORIZATION_HEADER]
            ?.first()
            ?.replace(BEARER_PREFIX, "")
            ?.trim()
            ?: return exchange.response
                .also { it.statusCode = HttpStatus.UNAUTHORIZED }
                .setComplete()

        return try {
            val isValid = jwtService.validateToken(token)
            if (isValid) {
                chain!!.filter(exchange)
            } else {
                throw AccessDeniedException("token is invalid")
            }
        } catch (e: Exception) {
            exchange.response
                .also { it.statusCode = HttpStatus.FORBIDDEN }
                .setComplete()
        }
    }

    private companion object {
        const val AUTHORIZATION_HEADER = "Authorization"
        const val BEARER_PREFIX = "Bearer"
    }
}