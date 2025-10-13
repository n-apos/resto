package com.workshops.resto.data.repositories

import com.workshops.resto.data.entities.Role
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository
import java.util.*

@Repository
interface RoleRepository : JpaRepository<Role, UUID> {
    fun findByName(name: String): Role?
}
