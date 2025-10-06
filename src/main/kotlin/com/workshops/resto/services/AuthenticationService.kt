package com.workshops.resto.services

import com.workshops.resto.api.dtos.LoginRequestDto
import com.workshops.resto.api.dtos.RegistrationRequestDto
import com.workshops.resto.api.dtos.UpdatePasswordRequestDto
import com.workshops.resto.data.entities.Credentials
import com.workshops.resto.data.entities.User
import com.workshops.resto.data.repositories.CredentialsRepository
import com.workshops.resto.util.JwtTokenUtil
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.security.authentication.AuthenticationManager
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken
import org.springframework.security.core.context.SecurityContextHolder
import org.springframework.security.core.userdetails.UserDetailsService
import org.springframework.security.crypto.password.PasswordEncoder
import org.springframework.stereotype.Service

@Service
class AuthenticationService @Autowired constructor(
    private val credentialsRepository: CredentialsRepository,
    private val passwordEncoder: PasswordEncoder,
    private val authenticationManager: AuthenticationManager,
    private val jwtTokenUtil: JwtTokenUtil,
    private val userDetailsService: UserDetailsService
) {

    fun register(registrationRequest: RegistrationRequestDto): User {
        val user = User(
            firstname = registrationRequest.firstname,
            lastname = registrationRequest.lastname,
        )
        var credentials = Credentials(
            username = registrationRequest.username,
            password = registrationRequest.password,
            user = user
        )
        val encodedPassword = passwordEncoder.encode(credentials.password)
        credentials = credentialsRepository.save(credentials.copy(password = encodedPassword))
        return credentials.user!!
    }

    fun login(loginRequest: LoginRequestDto): String {
        authenticationManager.authenticate(
            UsernamePasswordAuthenticationToken(loginRequest.username, loginRequest.password)
        )
        val userDetails = userDetailsService.loadUserByUsername(loginRequest.username)
        return jwtTokenUtil.generateToken(userDetails)
    }

    fun updatePassword(updatePasswordRequest: UpdatePasswordRequestDto) {
        val username = SecurityContextHolder.getContext().authentication.name
        val credentials = credentialsRepository.findByUsername(username).getOrThrow()

        if (!passwordEncoder.matches(updatePasswordRequest.oldPassword, credentials.password)) {
            throw Exception("Invalid old password")
        }

        val encodedNewPassword = passwordEncoder.encode(updatePasswordRequest.newPassword)
        credentialsRepository.save(credentials.copy(password = encodedNewPassword))
    }
}