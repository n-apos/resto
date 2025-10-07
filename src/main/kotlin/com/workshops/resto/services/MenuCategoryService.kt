package com.workshops.resto.services

import com.workshops.resto.data.entities.MenuCategory
import com.workshops.resto.data.repositories.MenuCategoryRepository
import org.springframework.stereotype.Service
import java.util.UUID

@Service
class MenuCategoryService(private val menuCategoryRepository: MenuCategoryRepository) {

    fun create(menuCategory: MenuCategory): MenuCategory {
        return menuCategoryRepository.save(menuCategory)
    }

    fun update(id: UUID, menuCategoryDetails: MenuCategory): MenuCategory {
        val existingMenuCategory = menuCategoryRepository.findById(id)
            .orElseThrow { Exception("MenuCategory not found with id: $id") }

        val updatedMenuCategory = existingMenuCategory.copy(
            menu = menuCategoryDetails.menu,
            category = menuCategoryDetails.category,
            maxItems = menuCategoryDetails.maxItems
        )

        return menuCategoryRepository.save(updatedMenuCategory)
    }

    fun getAll(): List<MenuCategory> {
        return menuCategoryRepository.findAll()
    }

    fun getById(id: UUID): MenuCategory {
        return menuCategoryRepository.findById(id)
            .orElseThrow { Exception("MenuCategory not found with id: $id") }
    }

    fun delete(id: UUID) {
        if (!menuCategoryRepository.existsById(id)) {
            throw Exception("MenuCategory not found with id: $id")
        }
        menuCategoryRepository.deleteById(id)
    }

    fun delete(ids: List<UUID>) {
        menuCategoryRepository.deleteAllById(ids)
    }

    fun deleteAll() {
        menuCategoryRepository.deleteAll()
    }
}
