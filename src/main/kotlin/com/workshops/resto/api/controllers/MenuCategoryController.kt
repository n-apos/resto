package com.workshops.resto.api.controllers

import com.workshops.resto.app.dtos.MenuCategoryDto
import com.workshops.resto.app.services.MenuCategoryService
import com.workshops.resto.data.entities.embeddables.MenuCategoryId
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.security.access.prepost.PreAuthorize
import org.springframework.web.bind.annotation.*
import java.util.*

@RestController
@RequestMapping("/api/v1/menu-categories")
class MenuCategoryController(private val menuCategoryService: MenuCategoryService) {

    @PostMapping
    @PreAuthorize("hasRole('ADMIN')")
    fun create(@RequestBody dto: MenuCategoryDto): ResponseEntity<MenuCategoryDto> {
        val createdLink = menuCategoryService.create(dto)
        return ResponseEntity.status(HttpStatus.CREATED).body(createdLink)
    }

    @PutMapping
    @PreAuthorize("hasRole('ADMIN')")
    fun update(@RequestBody dto: MenuCategoryDto): ResponseEntity<MenuCategoryDto> {
        val updatedLink = menuCategoryService.update(dto)
        return ResponseEntity.ok(updatedLink)
    }

    @GetMapping
    fun getAll(): ResponseEntity<List<MenuCategoryDto>> {
        val links = menuCategoryService.getAll()
        return ResponseEntity.ok(links)
    }

    @GetMapping("/{menuId}/{categoryId}")
    fun getById(@PathVariable menuId: UUID, @PathVariable categoryId: UUID): ResponseEntity<MenuCategoryDto> {
        val link = menuCategoryService.getById(menuId, categoryId)
        return ResponseEntity.ok(link)
    }

    @DeleteMapping("/{menuId}/{categoryId}")
    @PreAuthorize("hasRole('ADMIN')")
    fun delete(@PathVariable menuId: UUID, @PathVariable categoryId: UUID): ResponseEntity<Unit> {
        menuCategoryService.delete(menuId, categoryId)
        return ResponseEntity.noContent().build()
    }

    // Note: DELETE with a request body is non-standard but used here for simplicity.
    // A POST to a /delete sub-resource is an alternative.
    @DeleteMapping
    @PreAuthorize("hasRole('ADMIN')")
    fun deleteMany(@RequestBody ids: List<MenuCategoryId>): ResponseEntity<Unit> {
        menuCategoryService.delete(ids)
        return ResponseEntity.noContent().build()
    }

    @DeleteMapping("/all")
    @PreAuthorize("hasRole('ADMIN')")
    fun deleteAll(): ResponseEntity<Unit> {
        menuCategoryService.deleteAll()
        return ResponseEntity.noContent().build()
    }
}
