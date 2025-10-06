package com.workshops.resto.services

import com.workshops.resto.data.repositories.CredentialsRepository
import org.springframework.security.core.authority.SimpleGrantedAuthority
import org.springframework.security.core.userdetails.User
import org.springframework.security.core.userdetails.UserDetails
import org.springframework.security.core.userdetails.UserDetailsService
import org.springframework.stereotype.Service

@Service
class UserDetailsServiceImpl(
    private val credentialsRepository: CredentialsRepository,
) : UserDetailsService {

    override fun loadUserByUsername(username: String): UserDetails {
        val credentials = credentialsRepository.findByUsername(username)
            .getOrThrow()

        val user = credentials.user ?: throw IllegalStateException("User not found")

        return User(
            credentials.username,
            credentials.password,
            user.roles.map { SimpleGrantedAuthority(it.name) },
        )
    }


}