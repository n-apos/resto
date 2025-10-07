package com.workshops.resto.data.repositories

import com.workshops.resto.data.entities.MenuCategory
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository
import java.util.UUID

@Repository
interface MenuCategoryRepository : JpaRepository<MenuCategory, UUID> {
}
