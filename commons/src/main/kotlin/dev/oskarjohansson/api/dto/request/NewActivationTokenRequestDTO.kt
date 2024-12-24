package dev.oskarjohansson.api.dto.request

import org.springframework.validation.annotation.Validated


@Validated
data class NewActivationTokenRequestDTO(
 val email:String
)
