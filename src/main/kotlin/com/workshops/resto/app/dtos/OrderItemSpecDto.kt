package com.workshops.resto.app.dtos

import com.fasterxml.jackson.annotation.JsonProperty
import java.util.*

data class OrderItemSpecDto(
    @param:JsonProperty("item_id")
    val itemId: UUID? = null,

    @param:JsonProperty("meal_id")
    val mealId: UUID? = null
)
