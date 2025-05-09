package com.seunome.taskhub.controller

import com.seunome.taskhub.model.Task
import com.seunome.taskhub.service.TaskService
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping("/api/tasks")
class TaskController(private val taskService: TaskService)

    @GetMapping
    fun getAllTasks(): List<Task> = taskService.getAllTasks()

    @GetMapping("/{id}")
    fun getTaskById(@PathVariable id: Long): ResponseEntity<Task> =
        ResponseEntity.ok(taskService.getTaskById(id))

   @PostMapping
    fun createTask(@RequestBody @Valid taskDTO: TaskDTO): ResponseEntity<Task> {
        val project = projectRepository.findById(taskDTO.projectId)
            .orElseThrow { ProjectNotFoundException("Project with id ${taskDTO.projectId} not found") }

        val assignee = taskDTO.assigneeId?.let { userRepository.findById(it).orElse(null) }

        val task = Task(
            title = taskDTO.title,
            description = taskDTO.description,
            dueDate = taskDTO.dueDate,
            project = project,
            assignee = assignee
        )
        return ResponseEntity.ok(taskRepository.save(task))

    @DeleteMapping("/{id}")
    fun deleteTask(@PathVariable id: Long): ResponseEntity<Void> {
        taskService.deleteTask(id)
        return ResponseEntity.noContent().build()
    }
}


