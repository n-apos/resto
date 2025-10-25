package com.workshops.resto.api.controllers

import com.workshops.resto.app.dtos.MealDto
import com.workshops.resto.app.services.MealService
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.security.access.prepost.PreAuthorize
import org.springframework.web.bind.annotation.*
import java.util.UUID

@RestController
@RequestMapping("/api/v1/meals")
class MealController(private val mealService: MealService) {

    @PostMapping
    @PreAuthorize("hasRole('ADMIN')")
    fun create(@RequestBody dto: MealDto): ResponseEntity<MealDto> {
        val createdMeal = mealService.create(dto)
        return ResponseEntity.status(HttpStatus.CREATED).body(createdMeal)
    }

    @PutMapping("/{id}")
    @PreAuthorize("hasRole('ADMIN')")
    fun update(@PathVariable id: UUID, @RequestBody dto: MealDto): ResponseEntity<MealDto> {
        val updatedMeal = mealService.update(id, dto)
        return ResponseEntity.ok(updatedMeal)
    }

    @GetMapping
    fun getAll(): ResponseEntity<List<MealDto>> {
        val meals = mealService.getAll()
        return ResponseEntity.ok(meals)
    }

    @GetMapping("/{id}")
    fun getById(@PathVariable id: UUID): ResponseEntity<MealDto> {
        val meal = mealService.getById(id)
        return ResponseEntity.ok(meal)
    }

    @DeleteMapping("/{id}")
    @PreAuthorize("hasRole('ADMIN')")
    fun delete(@PathVariable id: UUID): ResponseEntity<Unit> {
        mealService.delete(id)
        return ResponseEntity.noContent().build()
    }

    @DeleteMapping
    @PreAuthorize("hasRole('ADMIN')")
    fun deleteMany(@RequestBody ids: List<UUID>): ResponseEntity<Unit> {
        mealService.delete(ids)
        return ResponseEntity.noContent().build()
    }

    @DeleteMapping("/all")
    @PreAuthorize("hasRole('ADMIN')")
    fun deleteAll(): ResponseEntity<Unit> {
        mealService.deleteAll()
        return ResponseEntity.noContent().build()
    }
}
