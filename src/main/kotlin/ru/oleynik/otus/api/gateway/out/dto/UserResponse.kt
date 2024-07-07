package ru.oleynik.otus.api.gateway.out.dto

data class UserResponse(
    val id: Long,
    var username: String,
    var firstName: String? = null,
    var lastName: String? = null,
    var email: String? = null,
    var phone: String? = null
)