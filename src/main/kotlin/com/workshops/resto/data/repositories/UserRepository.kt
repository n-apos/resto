package com.workshops.resto.data.repositories

import com.workshops.resto.data.entities.User
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.data.jpa.repository.Query
import org.springframework.stereotype.Repository
import java.util.*

@Repository
interface UserRepository : JpaRepository<User, UUID> {

    @Query(
        """
            SELECT DISTINCT u
            FROM User u,
                Credentials c
            WHERE u.id = c.user.id
            AND c.username = :username
            """
    )
    fun findByUsername(username: String): Result<User>

    @Query(
        """
            SELECT DISTINCT u
            FROM User u
            JOIN u.roles r
            WHERE r.name = 'ADMIN'
            """
    )
    fun getAdmins(): List<User>
}
