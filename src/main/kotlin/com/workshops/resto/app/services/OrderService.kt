package com.workshops.resto.app.services

import com.workshops.resto.data.entities.Order
import com.workshops.resto.data.repositories.OrderRepository
import org.springframework.stereotype.Service
import java.util.UUID

@Service
class OrderService(private val orderRepository: OrderRepository) {

    fun create(order: Order): Order {
        return orderRepository.save(order)
    }

    fun update(id: UUID, orderDetails: Order): Order {
        val existingOrder = orderRepository.findById(id)
            .orElseThrow { Exception("Order not found with id: $id") }

        val updatedOrder = existingOrder.copy(
            user = orderDetails.user,
            table = orderDetails.table,
            items = orderDetails.items
        )

        return orderRepository.save(updatedOrder)
    }

    fun getAll(): List<Order> {
        return orderRepository.findAll()
    }

    fun getById(id: UUID): Order {
        return orderRepository.findById(id)
            .orElseThrow { Exception("Order not found with id: $id") }
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
