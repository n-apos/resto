package com.workshops.resto.app.services

import com.workshops.resto.data.entities.OrderItem
import com.workshops.resto.data.repositories.OrderItemRepository
import org.springframework.stereotype.Service
import java.util.UUID

@Service
class OrderItemService(private val orderItemRepository: OrderItemRepository) {

    fun create(orderItem: OrderItem): OrderItem {
        return orderItemRepository.save(orderItem)
    }

    fun update(id: UUID, orderItemDetails: OrderItem): OrderItem {
        val existingOrderItem = orderItemRepository.findById(id)
            .orElseThrow { Exception("OrderItem not found with id: $id") }

        val updatedOrderItem = existingOrderItem.copy(
            order = orderItemDetails.order,
            item = orderItemDetails.item,
            menu = orderItemDetails.menu
        )

        return orderItemRepository.save(updatedOrderItem)
    }

    fun getAll(): List<OrderItem> {
        return orderItemRepository.findAll()
    }

    fun getById(id: UUID): OrderItem {
        return orderItemRepository.findById(id)
            .orElseThrow { Exception("OrderItem not found with id: $id") }
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
