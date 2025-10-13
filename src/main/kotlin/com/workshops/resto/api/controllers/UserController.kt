package com.workshops.resto.api.controllers

import com.workshops.resto.app.dtos.UserDto
import com.workshops.resto.app.services.UserService
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.security.access.prepost.PreAuthorize
import org.springframework.web.bind.annotation.*
import java.util.*

@RestController
@RequestMapping("/api/v1/users")
class UserController(private val userService: UserService) {

    // Note: User creation is handled by the public /api/auth/register endpoint

    @PutMapping
    @PreAuthorize("isAuthenticated()")
    fun update(@RequestBody dto: UserDto): ResponseEntity<UserDto> {
        val updatedUser = userService.update(dto)
        return ResponseEntity.ok(updatedUser)
    }

    @GetMapping
    fun getAll(): ResponseEntity<List<UserDto>> {
        val users = userService.getAll()
        return ResponseEntity.ok(users)
    }

    @GetMapping("/{id}")
    fun getById(@PathVariable id: UUID): ResponseEntity<UserDto> {
        // In a real application, you might want to restrict this so a user can only get their own data,
        // or only admins can get anyone's data.
        val user = userService.getById(id)
        return ResponseEntity.ok(user)
    }

    @GetMapping("/me")
    @PreAuthorize("isAuthenticated()")
    fun getMe(): ResponseEntity<UserDto> =
        ResponseEntity.ok(userService.getMe())

    @DeleteMapping("/{id}")
    @PreAuthorize("isAuthenticated()")
    fun delete(@PathVariable id: UUID): ResponseEntity<Unit> {
        userService.delete(id)
        return ResponseEntity.noContent().build()
    }

    @DeleteMapping
    @PreAuthorize("isAuthenticated()")
    fun deleteMany(@RequestBody ids: List<UUID>): ResponseEntity<Unit> {
        userService.delete(ids)
        return ResponseEntity.noContent().build()
    }

    @DeleteMapping("/all")
    @PreAuthorize("hasRole('ADMIN')") // Deleting all users should be an admin-only operation
    fun deleteAll(): ResponseEntity<Unit> {
        userService.deleteAll()
        return ResponseEntity.noContent().build()
    }

    @PostMapping("/{id}/roles")
    @PreAuthorize("hasRole('ADMIN')")
    fun updateUserRoles(@PathVariable id: UUID, @RequestBody roleIds: Set<String>): ResponseEntity<UserDto> {
        // This logic will need to be implemented in the UserService
        // For now, this is a placeholder for the endpoint structure.
        // val updatedUser = userService.updateRoles(id, roleIds)
        // return ResponseEntity.ok(updatedUser)
        return ResponseEntity.status(HttpStatus.NOT_IMPLEMENTED).build()
    }
}
