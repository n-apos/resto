package com.workshops.resto.app.services

import com.workshops.resto.data.entities.Table
import com.workshops.resto.data.repositories.TableRepository
import org.springframework.stereotype.Service
import java.util.UUID

@Service
class TableService(private val tableRepository: TableRepository) {

    fun create(table: Table): Table {
        return tableRepository.save(table)
    }

    fun update(id: UUID, tableDetails: Table): Table {
        val existingTable = tableRepository.findById(id)
            .orElseThrow { Exception("Table not found with id: $id") }

        val updatedTable = existingTable.copy(
            number = tableDetails.number
        )

        return tableRepository.save(updatedTable)
    }

    fun getAll(): List<Table> {
        return tableRepository.findAll()
    }

    fun getById(id: UUID): Table {
        return tableRepository.findById(id)
            .orElseThrow { Exception("Table not found with id: $id") }
    }

    fun delete(id: UUID) {
        if (!tableRepository.existsById(id)) {
            throw Exception("Table not found with id: $id")
        }
        tableRepository.deleteById(id)
    }

    fun delete(ids: List<UUID>) {
        tableRepository.deleteAllById(ids)
    }

    fun deleteAll() {
        tableRepository.deleteAll()
    }
}
