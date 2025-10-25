package com.workshops.resto.app.mappers

import com.workshops.resto.app.dtos.OrderMealDto
import com.workshops.resto.data.entities.Meal
import com.workshops.resto.data.entities.Order
import com.workshops.resto.data.entities.OrderMeal
import com.workshops.resto.util.ModelMapper
import org.springframework.stereotype.Component

@Component
class OrderMealMapper : ModelMapper<OrderMeal, OrderMealDto> {

    override fun toLayer(domain: OrderMeal): OrderMealDto =
        with(domain) {
            OrderMealDto(
                id = id,
                orderId = order?.id!!,
                mealId = meal?.id!!,
                quantity = quantity
            )
        }

    override fun toDomain(layer: OrderMealDto): OrderMeal =
        with(layer) {
            OrderMeal(
                id = id,
                order = Order(id = orderId),
                meal = Meal(id = mealId),
                quantity = quantity
            )
        }
}
