package com.workshops.resto.services

import com.workshops.resto.data.entities.Item
import com.workshops.resto.data.repositories.ItemRepository
import org.springframework.stereotype.Service
import java.util.UUID

@Service
class ItemService(private val itemRepository: ItemRepository) {

    fun create(item: Item): Item {
        return itemRepository.save(item)
    }

    fun update(id: UUID, itemDetails: Item): Item {
        val existingItem = itemRepository.findById(id)
            .orElseThrow { Exception("Item not found with id: $id") }

        val updatedItem = existingItem.copy(
            name = itemDetails.name,
            description = itemDetails.description,
            price = itemDetails.price,
            category = itemDetails.category
        )

        return itemRepository.save(updatedItem)
    }

    fun getAll(): List<Item> {
        return itemRepository.findAll()
    }

    fun getById(id: UUID): Item {
        return itemRepository.findById(id)
            .orElseThrow { Exception("Item not found with id: $id") }
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
