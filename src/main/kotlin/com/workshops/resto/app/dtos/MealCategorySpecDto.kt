package com.workshops.resto.app.dtos

import com.fasterxml.jackson.annotation.JsonProperty
import java.util.UUID

data class MealCategorySpecDto(
    @param:JsonProperty("category_id")
    val categoryId: UUID,

    @param:JsonProperty("max_items")
    val maxItems: Int
)
