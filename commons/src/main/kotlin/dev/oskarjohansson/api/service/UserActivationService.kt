package dev.oskarjohansson.api.service

import dev.oskarjohansson.api.dto.request.ActivationTokenRequestDto
import dev.oskarjohansson.api.dto.request.NewActivationTokenRequestDTO
import dev.oskarjohansson.model.ActivationToken
import dev.oskarjohansson.model.User
import dev.oskarjohansson.repository.ActivationTokenRepository
import dev.oskarjohansson.repository.UserRepository
import org.springframework.security.core.userdetails.UsernameNotFoundException
import org.springframework.stereotype.Service

@Service
class UserActivationService(
    private val userRepository: UserRepository,
    private val activationTokenRepository: ActivationTokenRepository,
    private val mailService: MailService
) {


    fun getUser(email: String): User {
        return userRepository.findByEmail(email) ?: throw throw UsernameNotFoundException("User not found")
    }

    fun newActivationToken(
        newActivationTokenRequestDTO: NewActivationTokenRequestDTO,
        hostAddress: String,
        moduleAddress: String
    ) {
        getUser(newActivationTokenRequestDTO.email).let { user ->
            if (!user.isEnabled) {
                activationTokenRepository.save(ActivationToken(email = user.email))
                    .let { token -> mailService.sendMail(token.token, token.email, hostAddress, moduleAddress) }
            }
        }
    }

    fun activateUser(activationToken: ActivationTokenRequestDto): User {
        if (!tokenPattern(activationToken.activationToken)) {
            throw IllegalArgumentException("Activation token is not valid")
        }
        val activationToken: ActivationToken = activationTokenRepository.findByToken(activationToken.activationToken)
            ?: throw IllegalStateException("Could not find token")
        val user: User = userRepository.findByEmail(activationToken.email)
            ?: throw UsernameNotFoundException("Could not find user")

        if (!user.isEnabled) {
            throw IllegalStateException("User is activated")
        }

        return userRepository.save(user.copy(isEnabled = true))
    }
}