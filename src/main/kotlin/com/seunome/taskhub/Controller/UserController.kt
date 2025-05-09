package com.seunome.taskhub.controller

import com.seunome.taskhub.model.User
import com.seunome.taskhub.repository.UserRepository
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping("/api/users")
class UserController(private val userRepository: UserRepository) {

    @GetMapping
    fun getAllUsers(): List<User> = userRepository.findAll()

    @GetMapping("/{id}")
    fun getUserById(@PathVariable id: Long): ResponseEntity<User> =
        userRepository.findById(id)
            .map { ResponseEntity.ok(it) }
            .orElse(ResponseEntity.notFound().build())

    @PostMapping
fun createUser(@RequestBody @Valid userDTO: UserDTO): User {
    val user = User(
        name = userDTO.name,
        email = userDTO.email,
        password = userDTO.password
    )
    return userRepository.save(user)
}

    @PutMapping("/{id}")
    fun updateUser(@PathVariable id: Long, @RequestBody updatedUser: User): ResponseEntity<User> {
        return userRepository.findById(id).map {
            val user = it.copy(name = updatedUser.name, email = updatedUser.email, password = updatedUser.password)
            ResponseEntity.ok(userRepository.save(user))
        }.orElse(ResponseEntity.notFound().build())
    }

    @DeleteMapping("/{id}")
    fun deleteUser(@PathVariable id: Long): ResponseEntity<Void> {
        return userRepository.findById(id).map {
            userRepository.deleteById(id)
            ResponseEntity.noContent().build()
        }.orElse(ResponseEntity.notFound().build())
    }
}

