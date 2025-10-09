package com.workshops.resto.app.dtos

import com.fasterxml.jackson.annotation.JsonProperty
import java.util.UUID

data class MenuDto(
    @param:JsonProperty("id")
    val id: UUID?,

    @param:JsonProperty("name")
    val name: String,

    @param:JsonProperty("description")
    val description: String,

    @param:JsonProperty("categories")
    val categories: List<MenuCategorySpecDto> = listOf()
)
