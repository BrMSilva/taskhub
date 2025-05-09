package com.seunome.taskhub.service

import com.seunome.taskhub.exception.ProjectNotFoundException
import com.seunome.taskhub.model.Project
import com.seunome.taskhub.repository.ProjectRepository
import org.springframework.stereotype.Service

@Service
class ProjectService(private val projectRepository: ProjectRepository) {

    fun getAllProjects(): List<Project> = projectRepository.findAll()

    fun getProjectById(id: Long): Project =
        projectRepository.findById(id).orElseThrow { ProjectNotFoundException("Project with id $id not found") }

    fun createProject(project: Project): Project = projectRepository.save(project)

    fun deleteProject(id: Long) {
        if (!projectRepository.existsById(id)) {
            throw ProjectNotFoundException("Project with id $id not found")
        }
        projectRepository.deleteById(id)
    }
}
