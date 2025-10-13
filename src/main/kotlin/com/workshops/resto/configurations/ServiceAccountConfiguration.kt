package com.workshops.resto.configurations

import com.workshops.resto.data.entities.Credentials
import com.workshops.resto.data.entities.Role
import com.workshops.resto.data.entities.User
import com.workshops.resto.data.repositories.CredentialsRepository
import com.workshops.resto.data.repositories.RoleRepository
import com.workshops.resto.data.repositories.UserRepository
import jakarta.annotation.PostConstruct
import org.springframework.context.annotation.Configuration
import org.springframework.security.crypto.password.PasswordEncoder

@Configuration
class ServiceAccountConfiguration(
    private val userRepository: UserRepository,
    private val credentialsRepository: CredentialsRepository,
    private val rolesRepository: RoleRepository,
    private val passwordEncoder: PasswordEncoder,
) {

    @PostConstruct
    fun createAdminIfNotExists() {
        val admins = userRepository.getAdmins()
        if (admins.isNotEmpty()) return

        val role = rolesRepository.findByName("ADMIN")
            ?: rolesRepository.save(Role(name = "ADMIN"))

        credentialsRepository.save(
            Credentials(
                username = "admin",
                password = passwordEncoder.encode("admin"),
                user = User(
                    id = null,
                    firstname = "admin",
                    lastname = "admin",
                    roles = mutableSetOf(role)
                )

            ),
        )
    }
}