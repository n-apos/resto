package com.workshops.resto.services

import com.workshops.resto.data.entities.MenuCategoryItem
import com.workshops.resto.data.repositories.MenuCategoryItemRepository
import org.springframework.stereotype.Service
import java.util.UUID

@Service
class MenuCategoryItemService(private val menuCategoryItemRepository: MenuCategoryItemRepository) {

    fun create(menuCategoryItem: MenuCategoryItem): MenuCategoryItem {
        return menuCategoryItemRepository.save(menuCategoryItem)
    }

    fun update(id: UUID, menuCategoryItemDetails: MenuCategoryItem): MenuCategoryItem {
        val existingMenuCategoryItem = menuCategoryItemRepository.findById(id)
            .orElseThrow { Exception("MenuCategoryItem not found with id: $id") }

        val updatedMenuCategoryItem = existingMenuCategoryItem.copy(
            item = menuCategoryItemDetails.item,
            menuCategory = menuCategoryItemDetails.menuCategory,
            additionalPrice = menuCategoryItemDetails.additionalPrice
        )

        return menuCategoryItemRepository.save(updatedMenuCategoryItem)
    }

    fun getAll(): List<MenuCategoryItem> {
        return menuCategoryItemRepository.findAll()
    }

    fun getById(id: UUID): MenuCategoryItem {
        return menuCategoryItemRepository.findById(id)
            .orElseThrow { Exception("MenuCategoryItem not found with id: $id") }
    }

    fun delete(id: UUID) {
        if (!menuCategoryItemRepository.existsById(id)) {
            throw Exception("MenuCategoryItem not found with id: $id")
        }
        menuCategoryItemRepository.deleteById(id)
    }

    fun delete(ids: List<UUID>) {
        menuCategoryItemRepository.deleteAllById(ids)
    }

    fun deleteAll() {
        menuCategoryItemRepository.deleteAll()
    }
}
