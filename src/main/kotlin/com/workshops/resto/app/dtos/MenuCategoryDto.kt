package com.workshops.resto.app.dtos

import com.fasterxml.jackson.annotation.JsonProperty
import java.util.UUID

data class MenuCategoryDto(
    @param:JsonProperty("category_id")
    val categoryId: UUID?,
    @param:JsonProperty("category_name")
    val categoryName: String,
    @param:JsonProperty("max_items")
    val maxItems: Int,
    @param:JsonProperty("items")
    val items: List<MenuCategoryItemDto>,
)
