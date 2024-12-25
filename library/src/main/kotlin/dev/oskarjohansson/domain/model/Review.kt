package dev.oskarjohansson.domain.model

import org.springframework.data.mongodb.core.mapping.Document
import org.springframework.data.mongodb.core.mapping.MongoId
import java.time.LocalDateTime

@Document(collection = "Reviews")
data class Review(
    @MongoId
    val reviewId: String? = null,
    val text: String,
    val rating: Int,
    val createdAt: LocalDateTime,
    val updatedAt: LocalDateTime? = null,
    val userId: String,
    val bookId: String
)
