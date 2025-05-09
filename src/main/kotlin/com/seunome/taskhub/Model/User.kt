package com.seunome.taskhub.model

import jakarta.persistence.*
import java.util.*

@Entity
@Table(name = "users")
data class User(
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    val id: Long = 0,

    val name: String,
    val email: String,
    val password: String,

    @OneToMany(mappedBy = "owner", cascade = [CascadeType.ALL], fetch = FetchType.LAZY)
    val projects: List<Project> = mutableListOf(),

    @OneToMany(mappedBy = "assignee", cascade = [CascadeType.ALL], fetch = FetchType.LAZY)
    val tasks: List<Task> = mutableListOf()
)
