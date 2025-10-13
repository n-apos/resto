package com.workshops.resto.app.services

import com.workshops.resto.app.dtos.ItemDto
import com.workshops.resto.app.mappers.ItemMapper
import com.workshops.resto.data.repositories.CategoryRepository
import com.workshops.resto.data.repositories.ItemRepository
import org.springframework.stereotype.Service
import java.util.*

@Service
class ItemService(
    private val itemRepository: ItemRepository,
    private val categoryRepository: CategoryRepository,
    private val itemMapper: ItemMapper
) {

    fun create(itemDto: ItemDto): ItemDto {
        val item = itemMapper.toDomain(itemDto)

        // Fetch and set the full category entity
        itemDto.categoryId?.let {
            val category = categoryRepository.findById(it)
                .orElseThrow { Exception("Category not found with id: ${itemDto.categoryId}") }
            item.category = category
        }

        val savedItem = itemRepository.save(item)
        return itemMapper.toLayer(savedItem)
    }

    fun update(id: UUID, itemDto: ItemDto): ItemDto {
        if (!itemRepository.existsById(id)) {
            throw Exception("Item not found with id: $id")
        }
        // Ensure the ID from the path is used for the update
        val item = itemMapper.toDomain(itemDto.copy(id = id))

        // Fetch and set the full category entity
        itemDto.categoryId?.let {
            val category = categoryRepository.findById(it)
                .orElseThrow { Exception("Category not found with id: ${itemDto.categoryId}") }
            item.category = category
        }

        val savedItem = itemRepository.save(item)
        return itemMapper.toLayer(savedItem)
    }

    fun getAll(): List<ItemDto> {
        return itemRepository.findAll().map(itemMapper::toLayer)
    }

    fun getById(id: UUID): ItemDto {
        val item = itemRepository.findById(id)
            .orElseThrow { Exception("Item not found with id: $id") }
        return itemMapper.toLayer(item)
    }

    fun delete(id: UUID) {
        if (!itemRepository.existsById(id)) {
            throw Exception("Item not found with id: $id")
        }
        itemRepository.deleteById(id)
    }

    fun delete(ids: List<UUID>) {
        itemRepository.deleteAllById(ids)
    }

    fun deleteAll() {
        itemRepository.deleteAll()
    }
}
