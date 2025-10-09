package com.workshops.resto.app.dtos

import com.fasterxml.jackson.annotation.JsonProperty
import java.util.UUID

data class OrderDto(
    @param:JsonProperty("id")
    val id: UUID?,

    @param:JsonProperty("user_id")
    val userId: UUID,

    @param:JsonProperty("table_id")
    val tableId: UUID,

    @param:JsonProperty("items")
    val items: List<OrderItemSpecDto> = listOf()
)
