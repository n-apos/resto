package com.workshops.resto.app.services

import com.workshops.resto.app.dtos.OrderItemDto
import com.workshops.resto.app.mappers.OrderItemMapper
import com.workshops.resto.data.repositories.ItemRepository
import com.workshops.resto.data.repositories.MealRepository
import com.workshops.resto.data.repositories.OrderItemRepository
import com.workshops.resto.data.repositories.OrderRepository
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional
import java.util.*

@Service
class OrderItemService(
    private val orderItemRepository: OrderItemRepository,
    private val orderRepository: OrderRepository,
    private val itemRepository: ItemRepository,
    private val mealRepository: MealRepository,
    private val orderItemMapper: OrderItemMapper
) {

    @Transactional
    fun create(dto: OrderItemDto): OrderItemDto {
        val orderItem = orderItemMapper.toDomain(dto)

        // Fetch and set the managed parent entities
        orderItem.order = orderRepository.findById(dto.orderId)
            .orElseThrow { Exception("Order not found with id: ${dto.orderId}") }

        dto.itemId?.let {
            orderItem.item = itemRepository.findById(it)
                .orElseThrow { Exception("Item not found with id: $it") }
        }
        dto.mealId?.let {
            orderItem.meal = mealRepository.findById(it)
                .orElseThrow { Exception("Menu not found with id: $it") }
        }

        val savedItem = orderItemRepository.save(orderItem)
        return orderItemMapper.toLayer(savedItem)
    }

    @Transactional
    fun update(id: UUID, dto: OrderItemDto): OrderItemDto {
        if (!orderItemRepository.existsById(id)) {
            throw Exception("OrderItem not found with id: $id")
        }
        val orderItem = orderItemMapper.toDomain(dto.copy(id = id))

        // Fetch and set the managed parent entities
        orderItem.order = orderRepository.findById(dto.orderId)
            .orElseThrow { Exception("Order not found with id: ${dto.orderId}") }

        orderItem.item = dto.itemId?.let {
            itemRepository.findById(it)
                .orElseThrow { Exception("Item not found with id: $it") }
        }
        orderItem.meal = dto.mealId?.let {
            mealRepository.findById(it)
                .orElseThrow { Exception("Menu not found with id: $it") }
        }

        val savedItem = orderItemRepository.save(orderItem)
        return orderItemMapper.toLayer(savedItem)
    }

    fun getAll(): List<OrderItemDto> {
        return orderItemRepository.findAll().map(orderItemMapper::toLayer)
    }

    fun getById(id: UUID): OrderItemDto {
        val orderItem = orderItemRepository.findById(id)
            .orElseThrow { Exception("OrderItem not found with id: $id") }
        return orderItemMapper.toLayer(orderItem)
    }

    fun delete(id: UUID) {
        if (!orderItemRepository.existsById(id)) {
            throw Exception("OrderItem not found with id: $id")
        }
        orderItemRepository.deleteById(id)
    }

    fun delete(ids: List<UUID>) {
        orderItemRepository.deleteAllById(ids)
    }

    fun deleteAll() {
        orderItemRepository.deleteAll()
    }
}
