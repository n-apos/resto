package com.workshops.resto.api.controllers

import com.workshops.resto.app.dtos.OrderDto
import com.workshops.resto.app.services.OrderService
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.security.access.prepost.PreAuthorize
import org.springframework.web.bind.annotation.*
import java.util.*

@RestController
@RequestMapping("/api/v1/orders")
class OrderController(private val orderService: OrderService) {

    @PostMapping
    @PreAuthorize("isAuthenticated()")
    fun create(@RequestBody dto: OrderDto): ResponseEntity<OrderDto> {
        val createdOrder = orderService.create(dto)
        return ResponseEntity.status(HttpStatus.CREATED).body(createdOrder)
    }

    @PutMapping("/{id}")
    @PreAuthorize("isAuthenticated()")
    fun update(@PathVariable id: UUID, @RequestBody dto: OrderDto): ResponseEntity<OrderDto> {
        val updatedOrder = orderService.update(id, dto)
        return ResponseEntity.ok(updatedOrder)
    }

    @GetMapping
    fun getAll(): ResponseEntity<List<OrderDto>> {
        val orders = orderService.getAll()
        return ResponseEntity.ok(orders)
    }

    @GetMapping("/{id}")
    fun getById(@PathVariable id: UUID): ResponseEntity<OrderDto> {
        val order = orderService.getById(id)
        return ResponseEntity.ok(order)
    }

    @DeleteMapping("/{id}")
    @PreAuthorize("isAuthenticated()")
    fun delete(@PathVariable id: UUID): ResponseEntity<Unit> {
        orderService.delete(id)
        return ResponseEntity.noContent().build()
    }

    @DeleteMapping
    @PreAuthorize("isAuthenticated()")
    fun deleteMany(@RequestBody ids: List<UUID>): ResponseEntity<Unit> {
        orderService.delete(ids)
        return ResponseEntity.noContent().build()
    }

    @DeleteMapping("/all")
    @PreAuthorize("hasRole('ADMIN')") // Deleting all orders should be an admin-only operation
    fun deleteAll(): ResponseEntity<Unit> {
        orderService.deleteAll()
        return ResponseEntity.noContent().build()
    }
}
