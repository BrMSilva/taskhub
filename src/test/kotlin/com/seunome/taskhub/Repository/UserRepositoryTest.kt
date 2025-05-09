package com.seunome.taskhub.repository

import org.junit.jupiter.api.Assertions.assertTrue
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Test
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest
import org.springframework.test.context.ActiveProfiles
import org.springframework.beans.factory.annotation.Autowired
import org.assertj.core.api.Assertions.assertThat
import com.seunome.taskhub.repository.UserRepository  
import com.seunome.taskhub.model.User   



@DataJpaTest
@ActiveProfiles("test")
class UserRepositoryTest @Autowired constructor(
    val userRepository: UserRepository
) {

    @Test
    fun `should save and load user`() {
        val user = User(name = "Ana Silva", email = "ana@example.com", password = "123456")
        val saved = userRepository.save(user)

        val found = userRepository.findById(saved.id)

        assertTrue(found.isPresent)
        assertEquals("Ana Silva", found.get().name)
    }
}
