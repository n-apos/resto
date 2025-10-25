package com.workshops.resto.data.repositories

import com.workshops.resto.data.entities.OrderMeal
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository
import java.util.UUID

@Repository
interface OrderMealRepository : JpaRepository<OrderMeal, UUID>
