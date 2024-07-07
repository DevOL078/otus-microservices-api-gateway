package ru.oleynik.otus.api.gateway.out.dto

data class UserRequest(
    var username: String,
    var password: String,
    var firstName: String? = null,
    var lastName: String? = null,
    var email: String? = null,
    var phone: String? = null
)