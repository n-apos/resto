package com.workshops.resto.app.services

import com.workshops.resto.app.dtos.MenuCategoryItemDto
import com.workshops.resto.app.mappers.MenuCategoryItemMapper
import com.workshops.resto.data.entities.embeddables.MenuCategoryId
import com.workshops.resto.data.entities.embeddables.MenuCategoryItemId
import com.workshops.resto.data.repositories.ItemRepository
import com.workshops.resto.data.repositories.MenuCategoryItemRepository
import com.workshops.resto.data.repositories.MenuCategoryRepository
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional
import java.util.*

@Service
class MenuCategoryItemService(
    private val menuCategoryItemRepository: MenuCategoryItemRepository,
    private val itemRepository: ItemRepository,
    private val menuCategoryRepository: MenuCategoryRepository,
    private val menuCategoryItemMapper: MenuCategoryItemMapper
) {

    @Transactional
    fun create(dto: MenuCategoryItemDto): MenuCategoryItemDto {
        val menuCategoryItem = menuCategoryItemMapper.toDomain(dto)

        // Fetch and set the managed parent entities
        val item = itemRepository.findById(dto.itemId)
            .orElseThrow { Exception("Item not found with id: ${dto.itemId}") }
        val menuCategoryId = MenuCategoryId(dto.menuId, dto.categoryId)
        val menuCategory = menuCategoryRepository.findById(menuCategoryId)
            .orElseThrow { Exception("MenuCategory not found with id: $menuCategoryId") }

        menuCategoryItem.item = item
        menuCategoryItem.menuCategory = menuCategory

        val savedItem = menuCategoryItemRepository.save(menuCategoryItem)
        return menuCategoryItemMapper.toLayer(savedItem)
    }

    @Transactional
    fun update(dto: MenuCategoryItemDto): MenuCategoryItemDto {
        val id = MenuCategoryItemId(dto.itemId, MenuCategoryId(dto.menuId, dto.categoryId))
        val existingItem = menuCategoryItemRepository.findById(id)
            .orElseThrow { Exception("MenuCategoryItem not found with id: $id") }

        // Only the additional price is updatable in this context
        existingItem.additionalPrice = dto.additionalPrice

        val savedItem = menuCategoryItemRepository.save(existingItem)
        return menuCategoryItemMapper.toLayer(savedItem)
    }

    fun getAll(): List<MenuCategoryItemDto> {
        return menuCategoryItemRepository.findAll().map(menuCategoryItemMapper::toLayer)
    }

    fun getById(menuId: UUID, categoryId: UUID, itemId: UUID): MenuCategoryItemDto {
        val id = MenuCategoryItemId(itemId, MenuCategoryId(menuId, categoryId))
        val item = menuCategoryItemRepository.findById(id)
            .orElseThrow { Exception("MenuCategoryItem not found with id: $id") }
        return menuCategoryItemMapper.toLayer(item)
    }

    fun delete(menuId: UUID, categoryId: UUID, itemId: UUID) {
        val id = MenuCategoryItemId(itemId, MenuCategoryId(menuId, categoryId))
        if (!menuCategoryItemRepository.existsById(id)) {
            throw Exception("MenuCategoryItem not found with id: $id")
        }
        menuCategoryItemRepository.deleteById(id)
    }

    fun delete(ids: List<MenuCategoryItemId>) {
        // The caller is responsible for constructing the list of composite IDs
        menuCategoryItemRepository.deleteAllById(ids)
    }

    fun deleteAll() {
        menuCategoryItemRepository.deleteAll()
    }
}
