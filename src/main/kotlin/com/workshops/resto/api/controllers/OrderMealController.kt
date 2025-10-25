package com.workshops.resto.api.controllers

import com.workshops.resto.app.dtos.OrderMealDto
import com.workshops.resto.app.services.OrderMealService
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.security.access.prepost.PreAuthorize
import org.springframework.web.bind.annotation.*
import java.util.UUID

@RestController
@RequestMapping("/api/v1/order-meals")
class OrderMealController(private val orderMealService: OrderMealService) {

    @PostMapping
    @PreAuthorize("isAuthenticated()")
    fun create(@RequestBody dto: OrderMealDto): ResponseEntity<OrderMealDto> {
        val createdOrderMeal = orderMealService.create(dto)
        return ResponseEntity.status(HttpStatus.CREATED).body(createdOrderMeal)
    }

    @PutMapping("/{id}")
    @PreAuthorize("isAuthenticated()")
    fun update(@PathVariable id: UUID, @RequestBody dto: OrderMealDto): ResponseEntity<OrderMealDto> {
        val updatedOrderMeal = orderMealService.update(id, dto)
        return ResponseEntity.ok(updatedOrderMeal)
    }

    @GetMapping
    fun getAll(): ResponseEntity<List<OrderMealDto>> {
        val orderMeals = orderMealService.getAll()
        return ResponseEntity.ok(orderMeals)
    }

    @GetMapping("/{id}")
    fun getById(@PathVariable id: UUID): ResponseEntity<OrderMealDto> {
        val orderMeal = orderMealService.getById(id)
        return ResponseEntity.ok(orderMeal)
    }

    @DeleteMapping("/{id}")
    @PreAuthorize("isAuthenticated()")
    fun delete(@PathVariable id: UUID): ResponseEntity<Unit> {
        orderMealService.delete(id)
        return ResponseEntity.noContent().build()
    }

    @DeleteMapping
    @PreAuthorize("isAuthenticated()")
    fun deleteMany(@RequestBody ids: List<UUID>): ResponseEntity<Unit> {
        orderMealService.delete(ids)
        return ResponseEntity.noContent().build()
    }

    @DeleteMapping("/all")
    @PreAuthorize("hasRole('ADMIN')") // Deleting all should still be an admin operation
    fun deleteAll(): ResponseEntity<Unit> {
        orderMealService.deleteAll()
        return ResponseEntity.noContent().build()
    }
}
