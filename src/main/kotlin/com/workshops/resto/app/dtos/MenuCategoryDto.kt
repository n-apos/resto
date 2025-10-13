package com.workshops.resto.app.dtos

import com.fasterxml.jackson.annotation.JsonProperty
import java.util.*

data class MenuCategoryDto(
    @param:JsonProperty("menu_id")
    val menuId: UUID,

    @param:JsonProperty("category_id")
    val categoryId: UUID,

    @param:JsonProperty("max_items")
    val maxItems: Int,

    @param:JsonProperty("items")
    val itemIds: List<UUID> = listOf()
)
