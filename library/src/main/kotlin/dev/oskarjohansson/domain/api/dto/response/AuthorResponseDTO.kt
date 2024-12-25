package dev.oskarjohansson.domain.api.dto.response

data class AuthorResponseDTO(
    val authorID: String,
    val authorName: String,
    val publishedBooks: List<BookInAuthorResponseDTO>
)
