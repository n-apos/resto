package com.workshops.resto.data.repositories

import com.workshops.resto.data.entities.MenuCategoryItem
import com.workshops.resto.data.entities.embeddables.MenuCategoryItemId
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository

@Repository
interface MenuCategoryItemRepository : JpaRepository<MenuCategoryItem, MenuCategoryItemId>
