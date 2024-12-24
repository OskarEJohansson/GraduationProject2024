package dev.oskarjohansson.api.service

import dev.oskarjohansson.model.ActivationToken
import io.ktor.http.*
import org.slf4j.Logger
import org.slf4j.LoggerFactory
import org.springframework.mail.SimpleMailMessage
import org.springframework.mail.javamail.JavaMailSender
import org.springframework.stereotype.Service

@Service
class MailService(
    private val mailSender: JavaMailSender,
    private val templateMessage: SimpleMailMessage
) {

    private val LOG: Logger = LoggerFactory.getLogger(MailService::class.java)


    fun sendMail(activationToken: String, emailAdress:String, hostAddress: String, moduleAddress:String ){

        val msg = SimpleMailMessage(templateMessage)
        msg.setTo(emailAdress)
        msg.text=

            ("Please activate your account by clicking the link: $hostAddress/$moduleAddress/$activationToken")

        runCatching {
            mailSender.send(msg)
        }.onFailure {
            LOG.debug("Error sending mail ${it.message}, to address: ${msg.to}")
        }.getOrThrow()
    }
}