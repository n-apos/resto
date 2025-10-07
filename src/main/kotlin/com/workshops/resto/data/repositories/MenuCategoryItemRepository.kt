package com.workshops.resto.data.repositories

import com.workshops.resto.data.entities.MenuCategoryItem
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository
import java.util.UUID

@Repository
interface MenuCategoryItemRepository : JpaRepository<MenuCategoryItem, UUID> {
}
