package com.workshops.resto.api.controllers

import com.workshops.resto.app.dtos.*
import com.workshops.resto.app.services.AuthenticationService
import org.springframework.http.ResponseEntity
import org.springframework.security.access.prepost.PreAuthorize
import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping("/api/v1/auth")
class AuthenticationController(
    private val authenticationService: AuthenticationService,
) {

    @PostMapping("/register")
    fun register(@RequestBody registrationRequest: RegistrationRequestDto): ResponseEntity<UserDto> {
        val user = authenticationService.register(registrationRequest)
        return ResponseEntity.ok(user)
    }

    @PostMapping("/login")
    fun login(@RequestBody loginRequest: LoginRequestDto): ResponseEntity<LoginResponseDto> {
        val response = authenticationService.login(loginRequest)
        return ResponseEntity.ok(response)
    }

    @PreAuthorize("isAuthenticated()")
    @PutMapping("/password")
    fun updatePassword(@RequestBody updatePasswordRequest: UpdatePasswordRequestDto): ResponseEntity<Unit> {
        authenticationService.updatePassword(updatePasswordRequest)
        return ResponseEntity.ok().build()
    }
}
