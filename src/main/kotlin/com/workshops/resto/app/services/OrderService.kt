package com.workshops.resto.app.services

import com.workshops.resto.app.dtos.OrderDto
import com.workshops.resto.app.mappers.OrderMapper
import com.workshops.resto.data.entities.OrderItem
import com.workshops.resto.data.repositories.*
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional
import java.util.*

@Service
class OrderService(
    private val orderRepository: OrderRepository,
    private val userRepository: UserRepository,
    private val tableRepository: TableRepository,
    private val itemRepository: ItemRepository,
    private val mealRepository: MealRepository,
    private val orderMapper: OrderMapper
) {

    @Transactional
    fun create(dto: OrderDto): OrderDto {
        val order = orderMapper.toDomain(dto)

        // Fetch and set the managed parent entities
        order.user = userRepository.findById(dto.userId)
            .orElseThrow { Exception("User not found with id: ${dto.userId}") }
        order.table = tableRepository.findById(dto.tableId)
            .orElseThrow { Exception("Table not found with id: ${dto.tableId}") }

        // Re-create the list of items with managed entities
        order.items.clear()
        dto.items.forEach { specDto ->
            val orderItem = OrderItem(order = order)
            specDto.itemId?.let {
                orderItem.item = itemRepository.findById(it)
                    .orElseThrow { Exception("Item not found with id: $it") }
            }
            specDto.mealId?.let {
                orderItem.meal = mealRepository.findById(it)
                    .orElseThrow { Exception("Menu not found with id: $it") }
            }
            order.items.add(orderItem)
        }

        val savedOrder = orderRepository.save(order)
        return orderMapper.toLayer(savedOrder)
    }

    @Transactional
    fun update(id: UUID, dto: OrderDto): OrderDto {
        val existingOrder = orderRepository.findById(id)
            .orElseThrow { Exception("Order not found with id: $id") }

        // Update relationships
        existingOrder.user = userRepository.findById(dto.userId)
            .orElseThrow { Exception("User not found with id: ${dto.userId}") }
        existingOrder.table = tableRepository.findById(dto.tableId)
            .orElseThrow { Exception("Table not found with id: ${dto.tableId}") }

        // Clear and rebuild the item list to handle additions, removals, and changes
        existingOrder.items.clear()
        dto.items.forEach { specDto ->
            val orderItem = OrderItem(order = existingOrder)
            specDto.itemId?.let {
                orderItem.item = itemRepository.findById(it)
                    .orElseThrow { Exception("Item not found with id: $it") }
            }
            specDto.mealId?.let {
                orderItem.meal = mealRepository.findById(it)
                    .orElseThrow { Exception("Menu not found with id: $it") }
            }
            existingOrder.items.add(orderItem)
        }

        val savedOrder = orderRepository.save(existingOrder)
        return orderMapper.toLayer(savedOrder)
    }

    fun getAll(): List<OrderDto> {
        return orderRepository.findAll().map(orderMapper::toLayer)
    }

    fun getById(id: UUID): OrderDto {
        val order = orderRepository.findById(id)
            .orElseThrow { Exception("Order not found with id: $id") }
        return orderMapper.toLayer(order)
    }

    fun delete(id: UUID) {
        if (!orderRepository.existsById(id)) {
            throw Exception("Order not found with id: $id")
        }
        orderRepository.deleteById(id)
    }

    fun delete(ids: List<UUID>) {
        orderRepository.deleteAllById(ids)
    }

    fun deleteAll() {
        orderRepository.deleteAll()
    }
}
