package com.seunome.taskhub.dto

import jakarta.validation.constraints.NotBlank
import jakarta.validation.constraints.NotNull
import java.time.LocalDateTime

data class TaskDTO(
    @field:NotBlank(message = "Title is required")
    val title: String,

    val description: String? = null,

    val dueDate: LocalDateTime? = null,

    @field:NotNull(message = "Project ID is required")
    val projectId: Long,

    val assigneeId: Long? = null
)
