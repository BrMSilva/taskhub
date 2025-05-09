package com.seunome.taskhub.controller

import com.seunome.taskhub.model.Project
import com.seunome.taskhub.service.ProjectService
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping("/api/projects")
class ProjectController(private val projectService: ProjectService) {

    @GetMapping
    fun getAllProjects(): List<Project> = projectService.getAllProjects()

    @GetMapping("/{id}")
    fun getProjectById(@PathVariable id: Long): ResponseEntity<Project> =
        ResponseEntity.ok(projectService.getProjectById(id))

     @PostMapping
    fun createProject(@RequestBody @Valid projectDTO: ProjectDTO): ResponseEntity<Project> {
        val owner = userRepository.findById(projectDTO.ownerId)
            .orElseThrow { UserNotFoundException("User with id ${projectDTO.ownerId} not found") }

        val project = Project(
            title = projectDTO.title,
            owner = owner
        )
        return ResponseEntity.ok(projectRepository.save(project))

    @PutMapping("/{id}")
    fun updateProject(@PathVariable id: Long, @RequestBody updated: Project): ResponseEntity<Project> {
        val existing = projectService.getProjectById(id)
        val updatedProject = existing.copy(title = updated.title)
        return ResponseEntity.ok(projectService.createProject(updatedProject))
    }

    @DeleteMapping("/{id}")
    fun deleteProject(@PathVariable id: Long): ResponseEntity<Void> {
        projectService.deleteProject(id)
        return ResponseEntity.noContent().build()
    }
}

