package com.workshops.resto.data.repositories

import com.workshops.resto.data.entities.Table
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository
import java.util.*

@Repository
interface TableRepository : JpaRepository<Table, UUID> {
    fun findByNumber(number: Int): Table?
}
