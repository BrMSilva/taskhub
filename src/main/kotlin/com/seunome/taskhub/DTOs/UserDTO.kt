package com.seunome.taskhub.dto

import jakarta.validation.constraints.Email
import jakarta.validation.constraints.NotBlank
import jakarta.validation.constraints.Size

data class UserDTO(
    @field:NotBlank(message = "Name is required")
    val name: String,

    @field:Email(message = "Invalid email format")
    val email: String,

    @field:Size(min = 6, message = "Password must be at least 6 characters")
    val password: String
)
