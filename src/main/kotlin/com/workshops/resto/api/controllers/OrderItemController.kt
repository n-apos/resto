package com.workshops.resto.api.controllers

import com.workshops.resto.app.dtos.OrderItemDto
import com.workshops.resto.app.services.OrderItemService
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.security.access.prepost.PreAuthorize
import org.springframework.web.bind.annotation.*
import java.util.*

@RestController
@RequestMapping("/api/v1/order-items")
class OrderItemController(private val orderItemService: OrderItemService) {

    @PostMapping
    @PreAuthorize("isAuthenticated()")
    fun create(@RequestBody dto: OrderItemDto): ResponseEntity<OrderItemDto> {
        val createdItem = orderItemService.create(dto)
        return ResponseEntity.status(HttpStatus.CREATED).body(createdItem)
    }

    @PutMapping("/{id}")
    @PreAuthorize("isAuthenticated()")
    fun update(@PathVariable id: UUID, @RequestBody dto: OrderItemDto): ResponseEntity<OrderItemDto> {
        val updatedItem = orderItemService.update(id, dto)
        return ResponseEntity.ok(updatedItem)
    }

    @GetMapping
    fun getAll(): ResponseEntity<List<OrderItemDto>> {
        val items = orderItemService.getAll()
        return ResponseEntity.ok(items)
    }

    @GetMapping("/{id}")
    fun getById(@PathVariable id: UUID): ResponseEntity<OrderItemDto> {
        val item = orderItemService.getById(id)
        return ResponseEntity.ok(item)
    }

    @DeleteMapping("/{id}")
    @PreAuthorize("isAuthenticated()")
    fun delete(@PathVariable id: UUID): ResponseEntity<Unit> {
        orderItemService.delete(id)
        return ResponseEntity.noContent().build()
    }

    @DeleteMapping
    @PreAuthorize("isAuthenticated()")
    fun deleteMany(@RequestBody ids: List<UUID>): ResponseEntity<Unit> {
        orderItemService.delete(ids)
        return ResponseEntity.noContent().build()
    }

    @DeleteMapping("/all")
    @PreAuthorize("hasRole('ADMIN')") // Deleting all order items should be an admin-only operation
    fun deleteAll(): ResponseEntity<Unit> {
        orderItemService.deleteAll()
        return ResponseEntity.noContent().build()
    }
}
