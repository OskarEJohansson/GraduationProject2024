package dev.oskarjohansson.api.service

import dev.oskarjohansson.api.dto.request.NewActivationTokenRequestDTO
import dev.oskarjohansson.model.ActivationToken
import dev.oskarjohansson.model.User
import dev.oskarjohansson.repository.ActivationTokenRepository
import dev.oskarjohansson.repository.UserRepository
import io.micrometer.common.util.internal.logging.InternalLogLevel
import org.springframework.security.core.userdetails.UsernameNotFoundException
import org.springframework.stereotype.Service

@Service
class UserActivationService(private val userRepository: UserRepository,
                            private val activationTokenRepository: ActivationTokenRepository,
                            private val mailService: MailService) {


    fun getUser(email:String): User {
        return userRepository.findByEmail(email) ?: throw throw UsernameNotFoundException("User not found")
    }

    fun newActivationToken(newActivationTokenRequestDTO: NewActivationTokenRequestDTO, hostAddress: String, moduleAddress: String){
        getUser(newActivationTokenRequestDTO.email).let {
                user ->
            if (!user.isEnabled) {
                activationTokenRepository.save(ActivationToken(email = user.email))
                    .let { token -> mailService.sendMail(token.token, token.email, hostAddress, moduleAddress) }
            }
        }



    }

    // TODO: Refactor activationToken method from admin
}