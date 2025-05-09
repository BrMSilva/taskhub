package com.seunome.taskhub.service

import com.seunome.taskhub.exception.ProjectNotFoundException
import com.seunome.taskhub.model.Task
import com.seunome.taskhub.repository.ProjectRepository
import com.seunome.taskhub.repository.TaskRepository
import org.springframework.stereotype.Service

@Service
class TaskService(
    private val taskRepository: TaskRepository,
    private val projectRepository: ProjectRepository
) {

    fun getAllTasks(): List<Task> = taskRepository.findAll()

    fun getTaskById(id: Long): Task =
        taskRepository.findById(id).orElseThrow { RuntimeException("Task with id $id not found") }

    fun createTask(task: Task): Task {
    val projectId = task.project?.id
        ?: throw IllegalArgumentException("Task must contain a valid project with ID")

    val project = projectRepository.findById(projectId).orElseThrow {
        ProjectNotFoundException("Project with id $projectId not found")
    }

    task.project = project
    return taskRepository.save(task)
}


    fun deleteTask(id: Long) {
        if (!taskRepository.existsById(id)) {
            throw RuntimeException("Task with id $id not found")
        }
        taskRepository.deleteById(id)
    }
}
