package com.workshops.resto.data.repositories

import com.workshops.resto.data.entities.OrderItem
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository
import java.util.UUID

@Repository
interface OrderItemRepository : JpaRepository<OrderItem, UUID> {
}
