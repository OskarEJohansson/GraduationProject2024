package dev.oskarjohansson.domain.api

import dev.oskarjohansson.api.dto.response.ResponseDTO
import dev.oskarjohansson.domain.service.AdminService
import dev.oskarjohansson.model.User
import jakarta.servlet.http.HttpServletRequest
import org.springframework.data.domain.Page
import org.springframework.data.domain.Pageable
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/admin/secured/v1")
class AdminController(private val adminService: AdminService) {

    @GetMapping("/users")
    fun users(httpServletRequest: HttpServletRequest, pageable: Pageable): ResponseEntity<ResponseDTO<Page<User>>> {
        return try {
            ResponseEntity.status(HttpStatus.OK).body(
                ResponseDTO(
                    HttpStatus.OK.value(),
                    "Users",
                    adminService.getUsers(pageable))
            )
        } catch (exception: IllegalStateException) {
            ResponseEntity.internalServerError().body(
                ResponseDTO(
                    HttpStatus.INTERNAL_SERVER_ERROR.value(),
                    "Error getting users, ${exception.message}"
                )
            )
        }
    }
}