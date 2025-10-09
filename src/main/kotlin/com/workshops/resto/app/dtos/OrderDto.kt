package com.workshops.resto.app.dtos

import com.fasterxml.jackson.annotation.JsonProperty
import java.util.UUID

data class OrderDto(
    @param:JsonProperty("id")
    val id: UUID?,
    @param:JsonProperty("table_number")
    val tableNumber: Int,
    @param:JsonProperty("items")
    val items: List<OrderItemDto>,
)
