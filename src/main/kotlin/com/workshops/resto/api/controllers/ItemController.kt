package com.workshops.resto.api.controllers

import com.workshops.resto.app.dtos.ItemDto
import com.workshops.resto.app.services.ItemService
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.security.access.prepost.PreAuthorize
import org.springframework.web.bind.annotation.*
import java.util.*

@RestController
@RequestMapping("/api/v1/items")
class ItemController(private val itemService: ItemService) {

    @PostMapping
    @PreAuthorize("hasRole('ADMIN')")
    fun create(@RequestBody dto: ItemDto): ResponseEntity<ItemDto> {
        val createdItem = itemService.create(dto)
        return ResponseEntity.status(HttpStatus.CREATED).body(createdItem)
    }

    @PutMapping("/{id}")
    @PreAuthorize("hasRole('ADMIN')")
    fun update(@PathVariable id: UUID, @RequestBody dto: ItemDto): ResponseEntity<ItemDto> {
        val updatedItem = itemService.update(id, dto)
        return ResponseEntity.ok(updatedItem)
    }

    @GetMapping
    fun getAll(): ResponseEntity<List<ItemDto>> {
        val items = itemService.getAll()
        return ResponseEntity.ok(items)
    }

    @GetMapping("/{id}")
    fun getById(@PathVariable id: UUID): ResponseEntity<ItemDto> {
        val item = itemService.getById(id)
        return ResponseEntity.ok(item)
    }

    @DeleteMapping("/{id}")
    @PreAuthorize("hasRole('ADMIN')")
    fun delete(@PathVariable id: UUID): ResponseEntity<Unit> {
        itemService.delete(id)
        return ResponseEntity.noContent().build()
    }

    @DeleteMapping
    @PreAuthorize("hasRole('ADMIN')")
    fun deleteMany(@RequestBody ids: List<UUID>): ResponseEntity<Unit> {
        itemService.delete(ids)
        return ResponseEntity.noContent().build()
    }

    @DeleteMapping("/all")
    @PreAuthorize("hasRole('ADMIN')")
    fun deleteAll(): ResponseEntity<Unit> {
        itemService.deleteAll()
        return ResponseEntity.noContent().build()
    }
}
