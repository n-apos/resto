package com.workshops.resto.app.services

import com.workshops.resto.app.dtos.MenuCategoryDto
import com.workshops.resto.app.mappers.MenuCategoryMapper
import com.workshops.resto.data.entities.MenuCategoryItem
import com.workshops.resto.data.entities.embeddables.MenuCategoryId
import com.workshops.resto.data.repositories.CategoryRepository
import com.workshops.resto.data.repositories.ItemRepository
import com.workshops.resto.data.repositories.MenuCategoryRepository
import com.workshops.resto.data.repositories.MenuRepository
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional
import java.util.*

@Service
class MenuCategoryService(
    private val menuCategoryRepository: MenuCategoryRepository,
    private val menuRepository: MenuRepository,
    private val categoryRepository: CategoryRepository,
    private val itemRepository: ItemRepository,
    private val menuCategoryMapper: MenuCategoryMapper
) {

    @Transactional
    fun create(dto: MenuCategoryDto): MenuCategoryDto {
        val menuCategory = menuCategoryMapper.toDomain(dto)

        // Fetch and set the managed parent entities
        val menu = menuRepository.findById(dto.menuId)
            .orElseThrow { Exception("Menu not found with id: ${dto.menuId}") }
        val category = categoryRepository.findById(dto.categoryId)
            .orElseThrow { Exception("Category not found with id: ${dto.categoryId}") }

        menuCategory.menu = menu
        menuCategory.category = category

        // Fetch and set the child item entities
        menuCategory.items.forEach { menuCategoryItem ->
            val item = itemRepository.findById(menuCategoryItem.item?.id!!)
                .orElseThrow { Exception("Item not found with id: ${menuCategoryItem.item?.id}") }
            menuCategoryItem.item = item
        }

        val savedMenuCategory = menuCategoryRepository.save(menuCategory)
        return menuCategoryMapper.toLayer(savedMenuCategory)
    }

    @Transactional
    fun update(dto: MenuCategoryDto): MenuCategoryDto {
        val id = MenuCategoryId(dto.menuId, dto.categoryId)
        val existingMenuCategory = menuCategoryRepository.findById(id)
            .orElseThrow { Exception("MenuCategory not found with id: $id") }

        // Update maxItems
        existingMenuCategory.maxItems = dto.maxItems

        // Clear existing items and rebuild the collection
        existingMenuCategory.items.clear()
        dto.itemIds.forEach { itemId ->
            val item = itemRepository.findById(itemId)
                .orElseThrow { Exception("Item not found with id: $itemId") }
            existingMenuCategory.items.add(
                MenuCategoryItem(
                    menuCategory = existingMenuCategory,
                    item = item
                )
            )
        }

        val savedMenuCategory = menuCategoryRepository.save(existingMenuCategory)
        return menuCategoryMapper.toLayer(savedMenuCategory)
    }

    fun getAll(): List<MenuCategoryDto> {
        return menuCategoryRepository.findAll().map(menuCategoryMapper::toLayer)
    }

    fun getById(menuId: UUID, categoryId: UUID): MenuCategoryDto {
        val id = MenuCategoryId(menuId, categoryId)
        val menuCategory = menuCategoryRepository.findById(id)
            .orElseThrow { Exception("MenuCategory not found with id: $id") }
        return menuCategoryMapper.toLayer(menuCategory)
    }

    fun delete(menuId: UUID, categoryId: UUID) {
        val id = MenuCategoryId(menuId, categoryId)
        if (!menuCategoryRepository.existsById(id)) {
            throw Exception("MenuCategory not found with id: $id")
        }
        menuCategoryRepository.deleteById(id)
    }

    fun delete(ids: List<MenuCategoryId>) {
        menuCategoryRepository.deleteAllById(ids)
    }

    fun deleteAll() {
        menuCategoryRepository.deleteAll()
    }
}
