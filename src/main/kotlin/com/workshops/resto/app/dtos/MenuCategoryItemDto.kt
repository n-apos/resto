package com.workshops.resto.app.dtos

import com.fasterxml.jackson.annotation.JsonProperty
import java.util.UUID

data class MenuCategoryItemDto(
    @param:JsonProperty("item_id")
    val itemId: UUID,
    @param:JsonProperty("item_name")
    val itemName: String,
    @param:JsonProperty("additional_price")
    val additionalPrice: Double,
)