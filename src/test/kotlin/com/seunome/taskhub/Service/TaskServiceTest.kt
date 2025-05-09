package com.seunome.taskhub.service

import com.seunome.taskhub.exception.ProjectNotFoundException
import com.seunome.taskhub.model.Task
import com.seunome.taskhub.model.User
import com.seunome.taskhub.repository.ProjectRepository
import com.seunome.taskhub.repository.TaskRepository
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
import org.mockito.InjectMocks
import org.mockito.Mock
import org.mockito.Mockito.`when`
import org.mockito.Mockito.mock
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.test.util.ReflectionTestUtils
import java.util.*
import org.junit.jupiter.api.Assertions.*

@SpringBootTest
class TaskServiceTest {

    @InjectMocks
    lateinit var taskService: TaskService

    @Mock
    lateinit var taskRepository: TaskRepository

    @Mock
    lateinit var projectRepository: ProjectRepository

    private lateinit var project: com.seunome.taskhub.model.Project
    private lateinit var user: User
    private lateinit var task: Task

    @BeforeEach
    fun setup() {
        // Cria um usuário para ser o 'assignee' da tarefa
        user = User(name = "Bruno", email = "bruno@email.com", password = "123456")
        
        // Cria um projeto para os testes
        project = com.seunome.taskhub.model.Project(
            id = 1L,
            title = "Test Project",
            owner = user // Usando o usuário como dono do projeto
        )

        // Cria uma task para os testes com assignee e project
        task = Task(
            title = "Test Task",
            description = "This is a test task",
            status = "TODO",
            project = project,  // Associa o projeto à tarefa
            assignee = user    // Atribui o usuário como assignee da tarefa
        )
    }

    @Test
    fun `should create a task successfully`() {
        // Mockando o repositório do projeto
        `when`(projectRepository.findById(project.id)).thenReturn(Optional.of(project))
        
        // Mockando o repositório da task
        `when`(taskRepository.save(task)).thenReturn(task)

        // Chama o método de criar tarefa
        val result = taskService.createTask(task)

        // Verifica se a tarefa foi criada com sucesso
        assertEquals("Test Task", result.title)
        assertEquals("Test Project", result.project?.title)
        assertEquals("Bruno", result.assignee?.name)
    }

    @Test
    fun `should throw ProjectNotFoundException when project not found`() {
        // Mockando o repositório do projeto para não encontrar o projeto
        `when`(projectRepository.findById(project.id)).thenReturn(Optional.empty())

        // Chama o método de criar tarefa e verifica se a exceção é lançada
        assertThrows(ProjectNotFoundException::class.java) {
            taskService.createTask(task)
        }
    }
}




