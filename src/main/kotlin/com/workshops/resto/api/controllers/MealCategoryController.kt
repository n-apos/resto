package com.workshops.resto.api.controllers

import com.workshops.resto.app.dtos.MealCategoryDto
import com.workshops.resto.app.services.MealCategoryService
import com.workshops.resto.data.entities.embeddables.MealCategoryId
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.security.access.prepost.PreAuthorize
import org.springframework.web.bind.annotation.*
import java.util.UUID

@RestController
@RequestMapping("/api/v1/meal-categories")
class MealCategoryController(private val mealCategoryService: MealCategoryService) {

    @PostMapping
    @PreAuthorize("hasRole('ADMIN')")
    fun create(@RequestBody dto: MealCategoryDto): ResponseEntity<MealCategoryDto> {
        val createdLink = mealCategoryService.create(dto)
        return ResponseEntity.status(HttpStatus.CREATED).body(createdLink)
    }

    @PutMapping
    @PreAuthorize("hasRole('ADMIN')")
    fun update(@RequestBody dto: MealCategoryDto): ResponseEntity<MealCategoryDto> {
        val updatedLink = mealCategoryService.update(dto)
        return ResponseEntity.ok(updatedLink)
    }

    @GetMapping
    fun getAll(): ResponseEntity<List<MealCategoryDto>> {
        val links = mealCategoryService.getAll()
        return ResponseEntity.ok(links)
    }

    @GetMapping("/{mealId}/{categoryId}")
    fun getById(@PathVariable mealId: UUID, @PathVariable categoryId: UUID): ResponseEntity<MealCategoryDto> {
        val link = mealCategoryService.getById(mealId, categoryId)
        return ResponseEntity.ok(link)
    }

    @DeleteMapping("/{mealId}/{categoryId}")
    @PreAuthorize("hasRole('ADMIN')")
    fun delete(@PathVariable mealId: UUID, @PathVariable categoryId: UUID): ResponseEntity<Unit> {
        mealCategoryService.delete(mealId, categoryId)
        return ResponseEntity.noContent().build()
    }

    @DeleteMapping
    @PreAuthorize("hasRole('ADMIN')")
    fun deleteMany(@RequestBody ids: List<MealCategoryId>): ResponseEntity<Unit> {
        mealCategoryService.delete(ids)
        return ResponseEntity.noContent().build()
    }

    @DeleteMapping("/all")
    @PreAuthorize("hasRole('ADMIN')")
    fun deleteAll(): ResponseEntity<Unit> {
        mealCategoryService.deleteAll()
        return ResponseEntity.noContent().build()
    }
}
