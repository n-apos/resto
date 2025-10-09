package com.workshops.resto.app.dtos

import com.fasterxml.jackson.annotation.JsonProperty

data class ItemDto(
    @param:JsonProperty("id")
    val id: String,
    @param:JsonProperty("name")
    val name: String,
    @param:JsonProperty("description")
    val description: String,
    @param:JsonProperty("price")
    val price: Double,
    @param:JsonProperty("category")
    val category: CategoryDto,
)
