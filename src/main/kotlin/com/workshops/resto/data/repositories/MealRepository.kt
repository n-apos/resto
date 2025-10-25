package com.workshops.resto.data.repositories

import com.workshops.resto.data.entities.Meal
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository
import java.util.UUID

@Repository
interface MealRepository : JpaRepository<Meal, UUID> {
    fun findByName(name: String): Meal?
}
