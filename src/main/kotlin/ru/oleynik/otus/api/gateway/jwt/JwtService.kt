package ru.oleynik.otus.api.gateway.jwt

import io.jsonwebtoken.Jwts
import org.springframework.stereotype.Component
import java.util.Date

@Component
class JwtService {

    fun generateToken(login: String): String {
        return Jwts.builder()
            .subject(login)
            .issuedAt(Date())
            .expiration(Date(System.currentTimeMillis() + 60 * 60 * 1000 * EXPIRATION_HOURS))
            .compact()
    }

    private companion object {
        const val EXPIRATION_HOURS = 5
    }

}