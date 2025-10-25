package com.workshops.resto.app.dtos

import com.fasterxml.jackson.annotation.JsonProperty
import java.util.UUID

data class MealCategoryItemDto(
    @param:JsonProperty("meal_id")
    val mealId: UUID,

    @param:JsonProperty("category_id")
    val categoryId: UUID,

    @param:JsonProperty("item_id")
    val itemId: UUID,

    @param:JsonProperty("additional_price")
    val additionalPrice: Double
)
