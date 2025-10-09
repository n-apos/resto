package com.workshops.resto.app.dtos

import com.fasterxml.jackson.annotation.JsonProperty

data class OrderItemDto(
    @param:JsonProperty("id")
    val id: String,
    @param:JsonProperty("item")
    val item: ItemDto?,
    @param:JsonProperty("menu_category_item")
    val menu: MenuCategoryItemDto?
)
