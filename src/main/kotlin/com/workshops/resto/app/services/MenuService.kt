package com.workshops.resto.app.services

import com.workshops.resto.data.entities.Menu
import com.workshops.resto.data.repositories.MenuRepository
import org.springframework.stereotype.Service
import java.util.UUID

@Service
class MenuService(private val menuRepository: MenuRepository) {

    fun create(menu: Menu): Menu {
        return menuRepository.save(menu)
    }

    fun update(id: UUID, menuDetails: Menu): Menu {
        val existingMenu = menuRepository.findById(id)
            .orElseThrow { Exception("Menu not found with id: $id") }

        val updatedMenu = existingMenu.copy(
            name = menuDetails.name,
            description = menuDetails.description,
            categories = menuDetails.categories
        )

        return menuRepository.save(updatedMenu)
    }

    fun getAll(): List<Menu> {
        return menuRepository.findAll()
    }

    fun getById(id: UUID): Menu {
        return menuRepository.findById(id)
            .orElseThrow { Exception("Menu not found with id: $id") }
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
