package dev.oskarjohansson.domain.service

import dev.oskarjohansson.api.dto.response.UserResponseDTO
import dev.oskarjohansson.domain.api.dto.request.UserRequestDTO
import dev.oskarjohansson.model.Role
import dev.oskarjohansson.model.User
import org.springframework.security.crypto.password.PasswordEncoder
import java.time.LocalDateTime


fun createUserObject(user: UserRequestDTO, passwordEncoder: PasswordEncoder): User = User(
        null,
        user.email,
        user.username,
        passwordEncoder.encode(user.password),
        Role.USER,
        LocalDateTime.now(),
        null
    )

fun User.createUserResponseDTO(): UserResponseDTO = UserResponseDTO(
        this.id,
        this.email,
        this.username,
       this.role
)