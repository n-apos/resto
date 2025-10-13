package com.workshops.resto.data.repositories

import com.workshops.resto.data.entities.Category
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository
import java.util.*

@Repository
interface CategoryRepository : JpaRepository<Category, UUID> {
    fun findByName(name: String): Category?
}
