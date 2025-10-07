package com.workshops.resto.data.repositories

import com.workshops.resto.data.entities.Item
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository
import java.util.UUID

@Repository
interface ItemRepository : JpaRepository<Item, UUID> {
    fun findByName(name: String): Item?
}
