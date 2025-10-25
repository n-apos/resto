package com.workshops.resto.app.services

import com.workshops.resto.app.dtos.OrderMealDto
import com.workshops.resto.app.mappers.OrderMealMapper
import com.workshops.resto.data.repositories.MealRepository
import com.workshops.resto.data.repositories.OrderMealRepository
import com.workshops.resto.data.repositories.OrderRepository
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional
import java.util.UUID

@Service
class OrderMealService(
    private val orderMealRepository: OrderMealRepository,
    private val orderRepository: OrderRepository,
    private val mealRepository: MealRepository,
    private val orderMealMapper: OrderMealMapper
) {

    @Transactional
    fun create(dto: OrderMealDto): OrderMealDto {
        val orderMeal = orderMealMapper.toDomain(dto)

        orderMeal.order = orderRepository.findById(dto.orderId)
            .orElseThrow { Exception("Order not found with id: ${dto.orderId}") }
        orderMeal.meal = mealRepository.findById(dto.mealId)
            .orElseThrow { Exception("Meal not found with id: ${dto.mealId}") }

        val savedOrderMeal = orderMealRepository.save(orderMeal)
        return orderMealMapper.toLayer(savedOrderMeal)
    }

    @Transactional
    fun update(id: UUID, dto: OrderMealDto): OrderMealDto {
        if (!orderMealRepository.existsById(id)) {
            throw Exception("OrderMeal not found with id: $id")
        }
        val orderMeal = orderMealMapper.toDomain(dto.copy(id = id))

        orderMeal.order = orderRepository.findById(dto.orderId)
            .orElseThrow { Exception("Order not found with id: ${dto.orderId}") }
        orderMeal.meal = mealRepository.findById(dto.mealId)
            .orElseThrow { Exception("Meal not found with id: ${dto.mealId}") }

        val savedOrderMeal = orderMealRepository.save(orderMeal)
        return orderMealMapper.toLayer(savedOrderMeal)
    }

    fun getAll(): List<OrderMealDto> {
        return orderMealRepository.findAll().map(orderMealMapper::toLayer)
    }

    fun getById(id: UUID): OrderMealDto {
        val orderMeal = orderMealRepository.findById(id)
            .orElseThrow { Exception("OrderMeal not found with id: $id") }
        return orderMealMapper.toLayer(orderMeal)
    }

    fun delete(id: UUID) {
        if (!orderMealRepository.existsById(id)) {
            throw Exception("OrderMeal not found with id: $id")
        }
        orderMealRepository.deleteById(id)
    }

    fun delete(ids: List<UUID>) {
        orderMealRepository.deleteAllById(ids)
    }

    fun deleteAll() {
        orderMealRepository.deleteAll()
    }
}
