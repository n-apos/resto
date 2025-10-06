package com.workshops.resto.data.repositories

import com.workshops.resto.data.entities.Credentials
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository
import java.util.*

@Repository
interface CredentialsRepository : JpaRepository<Credentials, UUID> {
    fun findByUsername(username: String): Result<Credentials>
}