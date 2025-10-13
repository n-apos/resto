package com.workshops.resto.api.controllers

import com.workshops.resto.app.dtos.CategoryDto
import com.workshops.resto.app.services.CategoryService
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.security.access.prepost.PreAuthorize
import org.springframework.web.bind.annotation.*
import java.util.*

@RestController
@RequestMapping("/api/v1/categories")
class CategoryController(private val categoryService: CategoryService) {

    @PostMapping
    @PreAuthorize("hasRole('ADMIN')")
    fun create(@RequestBody dto: CategoryDto): ResponseEntity<CategoryDto> {
        val createdCategory = categoryService.create(dto)
        return ResponseEntity.status(HttpStatus.CREATED).body(createdCategory)
    }

    @PutMapping("/{id}")
    @PreAuthorize("hasRole('ADMIN')")
    fun update(@PathVariable id: UUID, @RequestBody dto: CategoryDto): ResponseEntity<CategoryDto> {
        val updatedCategory = categoryService.update(id, dto)
        return ResponseEntity.ok(updatedCategory)
    }

    @GetMapping
    fun getAll(): ResponseEntity<List<CategoryDto>> {
        val categories = categoryService.getAll()
        return ResponseEntity.ok(categories)
    }

    @GetMapping("/{id}")
    fun getById(@PathVariable id: UUID): ResponseEntity<CategoryDto> {
        val category = categoryService.getById(id)
        return ResponseEntity.ok(category)
    }

    @DeleteMapping("/{id}")
    @PreAuthorize("hasRole('ADMIN')")
    fun delete(@PathVariable id: UUID): ResponseEntity<Unit> {
        categoryService.delete(id)
        return ResponseEntity.noContent().build()
    }

    @DeleteMapping
    @PreAuthorize("hasRole('ADMIN')")
    fun deleteMany(@RequestBody ids: List<UUID>): ResponseEntity<Unit> {
        categoryService.delete(ids)
        return ResponseEntity.noContent().build()
    }

    @DeleteMapping("/all")
    @PreAuthorize("hasRole('ADMIN')")
    fun deleteAll(): ResponseEntity<Unit> {
        categoryService.deleteAll()
        return ResponseEntity.noContent().build()
    }
}
