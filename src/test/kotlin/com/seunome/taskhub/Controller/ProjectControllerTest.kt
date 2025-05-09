package com.seunome.taskhub.controller

import com.fasterxml.jackson.databind.ObjectMapper
import com.seunome.taskhub.model.Project
import com.seunome.taskhub.model.User
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
class ProjectControllerTest {

    @Autowired lateinit var mockMvc: MockMvc
    @Autowired lateinit var objectMapper: ObjectMapper
    @Autowired lateinit var userRepository: UserRepository

    lateinit var user: User

    @BeforeEach
    fun setup() {
        user = userRepository.save(User(name = "Maria", email = "maria@email.com", password = "123"))
    }

    @Test
    fun `should create and return a project`() {
        val project = Project(title = "Novo Projeto", owner = user)

        mockMvc.perform(
            post("/api/projects")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(project))
        )
            .andExpect(status().isOk)
            .andExpect(jsonPath("$.title").value("Novo Projeto"))
    }

    @Test
    fun `should return all projects`() {
        mockMvc.perform(get("/api/projects"))
            .andExpect(status().isOk)
    }
}
