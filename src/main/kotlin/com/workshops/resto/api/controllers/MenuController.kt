package com.workshops.resto.api.controllers

import com.workshops.resto.app.dtos.MenuDto
import com.workshops.resto.app.services.MenuService
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.security.access.prepost.PreAuthorize
import org.springframework.web.bind.annotation.*
import java.util.*

@RestController
@RequestMapping("/api/v1/menus")
class MenuController(private val menuService: MenuService) {

    @PostMapping
    @PreAuthorize("hasRole('ADMIN')")
    fun create(@RequestBody dto: MenuDto): ResponseEntity<MenuDto> {
        val createdMenu = menuService.create(dto)
        return ResponseEntity.status(HttpStatus.CREATED).body(createdMenu)
    }

    @PutMapping("/{id}")
    @PreAuthorize("hasRole('ADMIN')")
    fun update(@PathVariable id: UUID, @RequestBody dto: MenuDto): ResponseEntity<MenuDto> {
        val updatedMenu = menuService.update(id, dto)
        return ResponseEntity.ok(updatedMenu)
    }

    @GetMapping
    fun getAll(): ResponseEntity<List<MenuDto>> {
        val menus = menuService.getAll()
        return ResponseEntity.ok(menus)
    }

    @GetMapping("/{id}")
    fun getById(@PathVariable id: UUID): ResponseEntity<MenuDto> {
        val menu = menuService.getById(id)
        return ResponseEntity.ok(menu)
    }

    @DeleteMapping("/{id}")
    @PreAuthorize("hasRole('ADMIN')")
    fun delete(@PathVariable id: UUID): ResponseEntity<Unit> {
        menuService.delete(id)
        return ResponseEntity.noContent().build()
    }

    @DeleteMapping
    @PreAuthorize("hasRole('ADMIN')")
    fun deleteMany(@RequestBody ids: List<UUID>): ResponseEntity<Unit> {
        menuService.delete(ids)
        return ResponseEntity.noContent().build()
    }

    @DeleteMapping("/all")
    @PreAuthorize("hasRole('ADMIN')")
    fun deleteAll(): ResponseEntity<Unit> {
        menuService.deleteAll()
        return ResponseEntity.noContent().build()
    }
}
