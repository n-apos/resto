package com.workshops.resto.app.mappers

import com.workshops.resto.app.dtos.OrderItemDto
import com.workshops.resto.data.entities.Item
import com.workshops.resto.data.entities.Menu
import com.workshops.resto.data.entities.Order
import com.workshops.resto.data.entities.OrderItem
import com.workshops.resto.util.ModelMapper
import org.springframework.stereotype.Component

@Component
class OrderItemMapper : ModelMapper<OrderItem, OrderItemDto> {

    override fun toLayer(domain: OrderItem): OrderItemDto =
        with(domain) {
            OrderItemDto(
                id = id,
                orderId = order?.id!!,
                itemId = item?.id,
                menuId = menu?.id
            )
        }

    override fun toDomain(layer: OrderItemDto): OrderItem =
        with(layer) {
            OrderItem(
                id = id,
                order = Order(id = orderId),
                item = itemId?.let { Item(id = it) },
                menu = menuId?.let { Menu(id = it) }
            )
        }
}
