package com.workshops.resto.data.repositories

import com.workshops.resto.data.entities.Order
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository
import java.util.*

@Repository
interface OrderRepository : JpaRepository<Order, UUID> {
}
