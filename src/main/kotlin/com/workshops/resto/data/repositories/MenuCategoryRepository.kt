package com.workshops.resto.data.repositories

import com.workshops.resto.data.entities.MenuCategory
import com.workshops.resto.data.entities.embeddables.MenuCategoryId
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository

@Repository
interface MenuCategoryRepository : JpaRepository<MenuCategory, MenuCategoryId>
