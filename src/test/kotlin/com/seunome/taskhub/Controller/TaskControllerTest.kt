package com.seunome.taskhub.controller

import com.fasterxml.jackson.databind.ObjectMapper
import com.seunome.taskhub.model.Project
import com.seunome.taskhub.model.Task
import com.seunome.taskhub.model.User
import com.seunome.taskhub.repository.ProjectRepository
import com.seunome.taskhub.repository.UserRepository
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.http.MediaType
import org.springframework.test.web.servlet.MockMvc
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*
import org.springframework.test.web.servlet.result.MockMvcResultMatchers.*

@SpringBootTest
@AutoConfigureMockMvc
class TaskControllerTest {

    @Autowired lateinit var mockMvc: MockMvc
    @Autowired lateinit var objectMapper: ObjectMapper
    @Autowired lateinit var userRepository: UserRepository
    @Autowired lateinit var projectRepository: ProjectRepository

    lateinit var user: User
    lateinit var project: Project

    @BeforeEach
    fun setup() {
        user = userRepository.save(User(name = "Carlos", email = "carlos@email.com", password = "123"))
        project = projectRepository.save(Project(title = "Projeto Tarefa", owner = user))
    }

    @Test
    fun `should create and return a task`() {
        val task = Task(title = "Tarefa 1", project = project, assignee = user)

        mockMvc.perform(
            post("/api/tasks")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(task))
        )
            .andExpect(status().isOk)
            .andExpect(jsonPath("$.title").value("Tarefa 1"))
    }

    @Test
    fun `should return all tasks`() {
        mockMvc.perform(get("/api/tasks"))
            .andExpect(status().isOk)
    }
}
