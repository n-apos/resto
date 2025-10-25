package com.workshops.resto.data.repositories

import com.workshops.resto.data.entities.MealCategoryItem
import com.workshops.resto.data.entities.embeddables.MealCategoryItemId
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository

@Repository
interface MealCategoryItemRepository : JpaRepository<MealCategoryItem, MealCategoryItemId>
