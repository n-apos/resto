package com.workshops.resto.app.services

import com.workshops.resto.app.dtos.MealCategoryItemDto
import com.workshops.resto.app.mappers.MealCategoryItemMapper
import com.workshops.resto.data.entities.embeddables.MealCategoryId
import com.workshops.resto.data.entities.embeddables.MealCategoryItemId
import com.workshops.resto.data.repositories.ItemRepository
import com.workshops.resto.data.repositories.MealCategoryItemRepository
import com.workshops.resto.data.repositories.MealCategoryRepository
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional
import java.util.UUID

@Service
class MealCategoryItemService(
    private val mealCategoryItemRepository: MealCategoryItemRepository,
    private val itemRepository: ItemRepository,
    private val mealCategoryRepository: MealCategoryRepository,
    private val mealCategoryItemMapper: MealCategoryItemMapper
) {

    @Transactional
    fun create(dto: MealCategoryItemDto): MealCategoryItemDto {
        val mealCategoryItem = mealCategoryItemMapper.toDomain(dto)

        val item = itemRepository.findById(dto.itemId)
            .orElseThrow { Exception("Item not found with id: ${dto.itemId}") }
        val mealCategoryId = MealCategoryId(dto.mealId, dto.categoryId)
        val mealCategory = mealCategoryRepository.findById(mealCategoryId)
            .orElseThrow { Exception("MealCategory not found with id: $mealCategoryId") }

        mealCategoryItem.item = item
        mealCategoryItem.mealCategory = mealCategory

        val savedItem = mealCategoryItemRepository.save(mealCategoryItem)
        return mealCategoryItemMapper.toLayer(savedItem)
    }

    @Transactional
    fun update(dto: MealCategoryItemDto): MealCategoryItemDto {
        val id = MealCategoryItemId(dto.itemId, MealCategoryId(dto.mealId, dto.categoryId))
        val existingItem = mealCategoryItemRepository.findById(id)
            .orElseThrow { Exception("MealCategoryItem not found with id: $id") }

        existingItem.additionalPrice = dto.additionalPrice

        val savedItem = mealCategoryItemRepository.save(existingItem)
        return mealCategoryItemMapper.toLayer(savedItem)
    }

    fun getAll(): List<MealCategoryItemDto> {
        return mealCategoryItemRepository.findAll().map(mealCategoryItemMapper::toLayer)
    }

    fun getById(mealId: UUID, categoryId: UUID, itemId: UUID): MealCategoryItemDto {
        val id = MealCategoryItemId(itemId, MealCategoryId(mealId, categoryId))
        val item = mealCategoryItemRepository.findById(id)
            .orElseThrow { Exception("MealCategoryItem not found with id: $id") }
        return mealCategoryItemMapper.toLayer(item)
    }

    fun delete(mealId: UUID, categoryId: UUID, itemId: UUID) {
        val id = MealCategoryItemId(itemId, MealCategoryId(mealId, categoryId))
        if (!mealCategoryItemRepository.existsById(id)) {
            throw Exception("MealCategoryItem not found with id: $id")
        }
        mealCategoryItemRepository.deleteById(id)
    }

    fun delete(ids: List<MealCategoryItemId>) {
        mealCategoryItemRepository.deleteAllById(ids)
    }

    fun deleteAll() {
        mealCategoryItemRepository.deleteAll()
    }
}
