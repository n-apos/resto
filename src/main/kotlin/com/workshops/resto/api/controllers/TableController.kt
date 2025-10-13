package com.workshops.resto.api.controllers

import com.workshops.resto.app.dtos.TableDto
import com.workshops.resto.app.services.TableService
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.security.access.prepost.PreAuthorize
import org.springframework.web.bind.annotation.*
import java.util.*

@RestController
@RequestMapping("/api/v1/tables")
class TableController(private val tableService: TableService) {

    @PostMapping
    @PreAuthorize("hasRole('ADMIN')")
    fun create(@RequestBody dto: TableDto): ResponseEntity<TableDto> {
        val createdTable = tableService.create(dto)
        return ResponseEntity.status(HttpStatus.CREATED).body(createdTable)
    }

    @PutMapping("/{id}")
    @PreAuthorize("hasRole('ADMIN')")
    fun update(@PathVariable id: UUID, @RequestBody dto: TableDto): ResponseEntity<TableDto> {
        val updatedTable = tableService.update(id, dto)
        return ResponseEntity.ok(updatedTable)
    }

    @GetMapping
    fun getAll(): ResponseEntity<List<TableDto>> {
        val tables = tableService.getAll()
        return ResponseEntity.ok(tables)
    }

    @GetMapping("/{id}")
    fun getById(@PathVariable id: UUID): ResponseEntity<TableDto> {
        val table = tableService.getById(id)
        return ResponseEntity.ok(table)
    }

    @DeleteMapping("/{id}")
    @PreAuthorize("hasRole('ADMIN')")
    fun delete(@PathVariable id: UUID): ResponseEntity<Unit> {
        tableService.delete(id)
        return ResponseEntity.noContent().build()
    }

    @DeleteMapping
    @PreAuthorize("hasRole('ADMIN')")
    fun deleteMany(@RequestBody ids: List<UUID>): ResponseEntity<Unit> {
        tableService.delete(ids)
        return ResponseEntity.noContent().build()
    }

    @DeleteMapping("/all")
    @PreAuthorize("hasRole('ADMIN')")
    fun deleteAll(): ResponseEntity<Unit> {
        tableService.deleteAll()
        return ResponseEntity.noContent().build()
    }
}
