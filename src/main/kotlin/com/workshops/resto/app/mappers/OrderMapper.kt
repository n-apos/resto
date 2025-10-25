package com.workshops.resto.app.mappers

import com.workshops.resto.app.dtos.OrderDto
import com.workshops.resto.app.dtos.OrderItemSpecDto
import com.workshops.resto.data.entities.Item
import com.workshops.resto.data.entities.Meal
import com.workshops.resto.data.entities.Order
import com.workshops.resto.data.entities.OrderItem
import com.workshops.resto.data.entities.Table
import com.workshops.resto.data.entities.User
import com.workshops.resto.util.ModelMapper
import org.springframework.stereotype.Component

@Component
class OrderMapper : ModelMapper<Order, OrderDto> {

    override fun toLayer(domain: Order): OrderDto =
        with(domain) {
            OrderDto(
                id = id,
                userId = user?.id!!,
                tableId = table?.id!!,
                items = items.map { orderItem ->
                    OrderItemSpecDto(
                        itemId = orderItem.item?.id,
                        mealId = orderItem.meal?.id
                    )
                }
            )
        }

    override fun toDomain(layer: OrderDto): Order =
        with(layer) {
            val order = Order(
                id = id,
                user = User(id = userId),
                table = Table(id = tableId)
            )
            val orderItems = items.map {
                OrderItem(
                    order = order,
                    // The service will fetch the full entities
                    item = it.itemId?.let { Item(id = it) },
                    meal = it.mealId?.let { Meal(id = it) }
                )
            }.toMutableList()
            order.items = orderItems
            order
        }
}
