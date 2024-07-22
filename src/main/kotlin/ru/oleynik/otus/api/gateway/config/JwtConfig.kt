package ru.oleynik.otus.api.gateway.config

import org.springframework.boot.context.properties.ConfigurationProperties
import java.security.interfaces.RSAPrivateKey

@ConfigurationProperties(prefix = "auth.jwt")
class JwtConfig(
    val privateKey: RSAPrivateKey,
)