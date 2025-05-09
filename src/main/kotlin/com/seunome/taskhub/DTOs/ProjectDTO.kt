package com.seunome.taskhub.dto

import jakarta.validation.constraints.NotBlank
import jakarta.validation.constraints.NotNull

data class ProjectDTO(
    @field:NotBlank(message = "Title is required")
    val title: String,

    @field:NotNull(message = "Owner ID is required")
    val ownerId: Long
)
