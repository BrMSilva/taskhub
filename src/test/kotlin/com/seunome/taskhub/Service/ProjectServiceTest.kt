package com.seunome.taskhub.service

import com.seunome.taskhub.exception.ProjectNotFoundException
import com.seunome.taskhub.model.Project
import com.seunome.taskhub.repository.ProjectRepository
import com.seunome.taskhub.repository.UserRepository
import org.junit.jupiter.api.Assertions.*
import org.junit.jupiter.api.Test
import org.mockito.Mock
import org.mockito.Mockito.*
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.beans.factory.annotation.Autowired
import com.seunome.taskhub.model.User


@SpringBootTest
class ProjectServiceTest {

    @Autowired
    lateinit var projectService: ProjectService

    @Autowired
    lateinit var userRepository: UserRepository // Dependência para salvar o usuário

    @Mock
    lateinit var projectRepository: ProjectRepository

    @Test
    fun `should create a project successfully`() {
        // Crie e salve o usuário antes de associá-lo ao projeto
        val user = User(name = "Bruno", email = "bruno@email.com", password = "123456")
        userRepository.save(user) // Salva o usuário no banco

        // Agora crie o projeto, associando o usuário salvo ao projeto
        val project = Project(title = "Projeto de Teste", owner = user)

        // Chame o método de serviço para salvar o projeto
        val result = projectService.createProject(project)

        // Verifique se o projeto foi salvo corretamente
        assertEquals("Projeto de Teste", result.title)
    }

    @Test
    fun `should throw ProjectNotFoundException when project not found`() {
        val nonExistingId = 999L

        assertThrows(ProjectNotFoundException::class.java) {
            projectService.getProjectById(nonExistingId)
        }
    }
}

