package com.workshops.resto.app.dtos

import com.fasterxml.jackson.annotation.JsonProperty
import java.util.*

data class MenuCategoryItemDto(
    @param:JsonProperty("menu_id")
    val menuId: UUID,

    @param:JsonProperty("category_id")
    val categoryId: UUID,

    @param:JsonProperty("item_id")
    val itemId: UUID,

    @param:JsonProperty("additional_price")
    val additionalPrice: Double
)
