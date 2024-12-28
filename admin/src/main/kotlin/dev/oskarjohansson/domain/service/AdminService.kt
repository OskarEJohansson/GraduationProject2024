package dev.oskarjohansson.domain.service

import dev.oskarjohansson.model.User
import dev.oskarjohansson.repository.UserRepository
import org.springframework.data.domain.Page
import org.springframework.data.domain.Pageable
import org.springframework.stereotype.Service

@Service
class AdminService(private val userRepository: UserRepository) {


    fun getUsers(pageable: Pageable): Page<User> {
        return userRepository.findAll(pageable)
    }
}