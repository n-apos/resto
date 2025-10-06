package com.workshops.resto.api.controllers

import com.workshops.resto.api.dtos.LoginRequestDto
import com.workshops.resto.api.dtos.LoginResponseDto
import com.workshops.resto.api.dtos.RegistrationRequestDto
import com.workshops.resto.api.dtos.UpdatePasswordRequestDto
import com.workshops.resto.api.dtos.UserDto
import com.workshops.resto.api.mappers.UserMapper
import com.workshops.resto.services.AuthenticationService
import org.springframework.http.ResponseEntity
import org.springframework.security.access.prepost.PreAuthorize
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.PutMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/api/v1/auth")
class AuthenticationController(
    private val authenticationService: AuthenticationService,
    private val userMapper: UserMapper
) {

    @PostMapping("/register")
    fun register(@RequestBody registrationRequest: RegistrationRequestDto): ResponseEntity<UserDto> {
        val user = authenticationService.register(registrationRequest)
        return ResponseEntity.ok(userMapper.toLayer(user))
    }

    @PostMapping("/login")
    fun login(@RequestBody loginRequest: LoginRequestDto): ResponseEntity<LoginResponseDto> {
        val token = authenticationService.login(loginRequest)
        return ResponseEntity.ok(LoginResponseDto(token))
    }

    @PreAuthorize("isAuthenticated()")
    @PutMapping("/password")
    fun updatePassword(@RequestBody updatePasswordRequest: UpdatePasswordRequestDto): ResponseEntity<Unit> {
        authenticationService.updatePassword(updatePasswordRequest)
        return ResponseEntity.ok().build()
    }
}
