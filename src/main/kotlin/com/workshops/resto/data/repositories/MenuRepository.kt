package com.workshops.resto.data.repositories

import com.workshops.resto.data.entities.Menu
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository
import java.util.*

@Repository
interface MenuRepository : JpaRepository<Menu, UUID> {
    fun findByName(name: String): Menu?
}
