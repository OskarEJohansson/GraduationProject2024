package dev.oskarjohansson.api.dto.response

data class ResponseDTO<T>(val status: Int, val message:String? = null, val data:T? = null)
