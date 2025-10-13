package com.workshops.resto.app.services

import com.workshops.resto.app.dtos.MenuDto
import com.workshops.resto.app.mappers.MenuMapper
import com.workshops.resto.data.entities.MenuCategory
import com.workshops.resto.data.repositories.CategoryRepository
import com.workshops.resto.data.repositories.MenuRepository
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional
import java.util.*

@Service
class MenuService(
    private val menuRepository: MenuRepository,
    private val categoryRepository: CategoryRepository,
    private val menuMapper: MenuMapper
) {

    @Transactional
    fun create(menuDto: MenuDto): MenuDto {
        val menu = menuMapper.toDomain(menuDto)

        // Replace transient categories with managed entities from the database
        menu.categories.forEach { menuCategory ->
            val category = categoryRepository.findById(menuCategory.category?.id!!)
                .orElseThrow { Exception("Category not found with id: ${menuCategory.category?.id}") }
            menuCategory.category = category
        }

        val savedMenu = menuRepository.save(menu)
        return menuMapper.toLayer(savedMenu)
    }

    @Transactional
    fun update(id: UUID, menuDto: MenuDto): MenuDto {
        val existingMenu = menuRepository.findById(id)
            .orElseThrow { Exception("Menu not found with id: $id") }

        // Update simple properties
        existingMenu.name = menuDto.name
        existingMenu.description = menuDto.description

        // Clear existing categories and re-add them based on the DTO
        // This handles additions, removals, and changes to maxItems
        existingMenu.categories.clear()
        menuDto.categories.forEach { specDto ->
            val category = categoryRepository.findById(specDto.categoryId)
                .orElseThrow { Exception("Category not found with id: ${specDto.categoryId}") }
            existingMenu.categories.add(
                MenuCategory(
                    menu = existingMenu,
                    category = category,
                    maxItems = specDto.maxItems
                )
            )
        }

        val savedMenu = menuRepository.save(existingMenu)
        return menuMapper.toLayer(savedMenu)
    }

    fun getAll(): List<MenuDto> {
        return menuRepository.findAll().map(menuMapper::toLayer)
    }

    fun getById(id: UUID): MenuDto {
        val menu = menuRepository.findById(id)
            .orElseThrow { Exception("Menu not found with id: $id") }
        return menuMapper.toLayer(menu)
    }

    fun delete(id: UUID) {
        if (!menuRepository.existsById(id)) {
            throw Exception("Menu not found with id: $id")
        }
        menuRepository.deleteById(id)
    }

    fun delete(ids: List<UUID>) {
        menuRepository.deleteAllById(ids)
    }

    fun deleteAll() {
        menuRepository.deleteAll()
    }
}
