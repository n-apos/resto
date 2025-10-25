package com.workshops.resto.app.dtos

import com.fasterxml.jackson.annotation.JsonProperty
import java.util.UUID

data class OrderMealDto(
    @param:JsonProperty("id")
    val id: UUID?,

    @param:JsonProperty("order_id")
    val orderId: UUID,

    @param:JsonProperty("meal_id")
    val mealId: UUID,

    @param:JsonProperty("quantity")
    val quantity: Int
)
