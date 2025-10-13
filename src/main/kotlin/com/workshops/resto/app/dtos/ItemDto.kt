package com.workshops.resto.app.dtos

import com.fasterxml.jackson.annotation.JsonProperty
import java.util.*

data class ItemDto(
    @param:JsonProperty("id")
    val id: UUID?,

    @param:JsonProperty("name")
    val name: String,

    @param:JsonProperty("description")
    val description: String,

    @param:JsonProperty("price")
    val price: Double,

    @param:JsonProperty("category_id")
    val categoryId: UUID?
)
