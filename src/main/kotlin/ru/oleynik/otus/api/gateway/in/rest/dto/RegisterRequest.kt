package ru.oleynik.otus.api.gateway.`in`.rest.dto

import ru.oleynik.otus.api.gateway.out.dto.UserRequest

class RegisterRequest(
    var username: String,
    var password: String,
    var firstName: String? = null,
    var lastName: String? = null,
    var email: String? = null,
    var phone: String? = null
)

fun RegisterRequest.toUserRequest(): UserRequest = UserRequest(
    username = this.username,
    password = this.password,
    firstName = this.firstName,
    lastName = this.lastName,
    email = this.email,
    phone = this.phone,
)