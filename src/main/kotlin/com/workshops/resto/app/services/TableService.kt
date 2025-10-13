package com.workshops.resto.app.services

import com.workshops.resto.app.dtos.TableDto
import com.workshops.resto.app.mappers.TableMapper
import com.workshops.resto.data.repositories.TableRepository
import org.springframework.stereotype.Service
import java.util.*

@Service
class TableService(
    private val tableRepository: TableRepository,
    private val tableMapper: TableMapper
) {

    fun create(dto: TableDto): TableDto {
        val entity = tableMapper.toDomain(dto)
        val savedEntity = tableRepository.save(entity)
        return tableMapper.toLayer(savedEntity)
    }

    fun update(id: UUID, dto: TableDto): TableDto {
        if (!tableRepository.existsById(id)) {
            throw Exception("Table not found with id: $id")
        }
        val entity = tableMapper.toDomain(dto.copy(id = id))
        val savedEntity = tableRepository.save(entity)
        return tableMapper.toLayer(savedEntity)
    }

    fun getAll(): List<TableDto> {
        return tableRepository.findAll().map(tableMapper::toLayer)
    }

    fun getById(id: UUID): TableDto {
        val entity = tableRepository.findById(id)
            .orElseThrow { Exception("Table not found with id: $id") }
        return tableMapper.toLayer(entity)
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
