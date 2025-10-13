package com.workshops.resto.app.dtos

import com.fasterxml.jackson.annotation.JsonProperty
import java.util.*

data class OrderItemDto(
    @param:JsonProperty("id")
    val id: UUID?,

    @param:JsonProperty("order_id")
    val orderId: UUID,

    @param:JsonProperty("item_id")
    val itemId: UUID? = null,

    @param:JsonProperty("menu_id")
    val menuId: UUID? = null
)
