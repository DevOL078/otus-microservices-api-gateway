package ru.oleynik.otus.api.gateway.jwt

import io.jsonwebtoken.Jwts
import org.springframework.http.HttpStatus
import org.springframework.stereotype.Component
import org.springframework.web.server.ResponseStatusException
import ru.oleynik.otus.api.gateway.config.JwtConfig
import java.util.Date

@Component
class JwtService(
    private val jwtConfig: JwtConfig,
) {

    fun generateToken(login: String): String {
        return Jwts.builder()
            .subject(login)
            .issuedAt(Date())
            .expiration(Date(System.currentTimeMillis() + 60 * 60 * 1000 * EXPIRATION_HOURS))
            .signWith(jwtConfig.privateKey)
            .compact()
    }

    fun validateToken(token: String): Boolean {
        val jwtParser = Jwts.parser()
            .decryptWith(jwtConfig.privateKey)
            .build()
        try {
            return jwtParser.isSigned(token)
        } catch (e: Exception) {
            throw ResponseStatusException(HttpStatus.UNAUTHORIZED)
        }
    }

    private companion object {
        const val EXPIRATION_HOURS = 5
    }

}