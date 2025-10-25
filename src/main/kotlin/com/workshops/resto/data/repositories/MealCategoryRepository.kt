package com.workshops.resto.data.repositories

import com.workshops.resto.data.entities.MealCategory
import com.workshops.resto.data.entities.embeddables.MealCategoryId
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository

@Repository
interface MealCategoryRepository : JpaRepository<MealCategory, MealCategoryId>
