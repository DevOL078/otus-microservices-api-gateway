package ru.oleynik.otus.api.gateway.auth

import org.springframework.cloud.context.config.annotation.RefreshScope
import org.springframework.cloud.gateway.filter.GatewayFilter
import org.springframework.cloud.gateway.filter.GatewayFilterChain
import org.springframework.stereotype.Component
import org.springframework.web.server.ServerWebExchange
import reactor.core.publisher.Mono

@RefreshScope
@Component
class AuthFilter : GatewayFilter {
    override fun filter(exchange: ServerWebExchange?, chain: GatewayFilterChain?): Mono<Void> {
//        TODO("Not yet implemented")
        println("Auth filter")
        return chain!!.filter(exchange)
    }
}