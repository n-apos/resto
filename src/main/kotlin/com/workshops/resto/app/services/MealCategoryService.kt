package com.workshops.resto.app.services

import com.workshops.resto.app.dtos.MealCategoryDto
import com.workshops.resto.app.mappers.MealCategoryMapper
import com.workshops.resto.data.entities.MealCategoryItem
import com.workshops.resto.data.entities.embeddables.MealCategoryId
import com.workshops.resto.data.repositories.CategoryRepository
import com.workshops.resto.data.repositories.ItemRepository
import com.workshops.resto.data.repositories.MealCategoryRepository
import com.workshops.resto.data.repositories.MealRepository
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional
import java.util.UUID

@Service
class MealCategoryService(
    private val mealCategoryRepository: MealCategoryRepository,
    private val mealRepository: MealRepository,
    private val categoryRepository: CategoryRepository,
    private val itemRepository: ItemRepository,
    private val mealCategoryMapper: MealCategoryMapper
) {

    @Transactional
    fun create(dto: MealCategoryDto): MealCategoryDto {
        val mealCategory = mealCategoryMapper.toDomain(dto)

        val meal = mealRepository.findById(dto.mealId)
            .orElseThrow { Exception("Meal not found with id: ${dto.mealId}") }
        val category = categoryRepository.findById(dto.categoryId)
            .orElseThrow { Exception("Category not found with id: ${dto.categoryId}") }

        mealCategory.meal = meal
        mealCategory.category = category

        mealCategory.items.forEach { mealCategoryItem ->
            val item = itemRepository.findById(mealCategoryItem.item?.id!!)
                .orElseThrow { Exception("Item not found with id: ${mealCategoryItem.item?.id}") }
            mealCategoryItem.item = item
        }

        val savedMealCategory = mealCategoryRepository.save(mealCategory)
        return mealCategoryMapper.toLayer(savedMealCategory)
    }

    @Transactional
    fun update(dto: MealCategoryDto): MealCategoryDto {
        val id = MealCategoryId(dto.mealId, dto.categoryId)
        val existingMealCategory = mealCategoryRepository.findById(id)
            .orElseThrow { Exception("MealCategory not found with id: $id") }

        existingMealCategory.maxItems = dto.maxItems

        existingMealCategory.items.clear()
        dto.itemIds.forEach { itemId ->
            val item = itemRepository.findById(itemId)
                .orElseThrow { Exception("Item not found with id: $itemId") }
            existingMealCategory.items.add(
                MealCategoryItem(
                    mealCategory = existingMealCategory,
                    item = item
                )
            )
        }

        val savedMealCategory = mealCategoryRepository.save(existingMealCategory)
        return mealCategoryMapper.toLayer(savedMealCategory)
    }

    fun getAll(): List<MealCategoryDto> {
        return mealCategoryRepository.findAll().map(mealCategoryMapper::toLayer)
    }

    fun getById(mealId: UUID, categoryId: UUID): MealCategoryDto {
        val id = MealCategoryId(mealId, categoryId)
        val mealCategory = mealCategoryRepository.findById(id)
            .orElseThrow { Exception("MealCategory not found with id: $id") }
        return mealCategoryMapper.toLayer(mealCategory)
    }

    fun delete(mealId: UUID, categoryId: UUID) {
        val id = MealCategoryId(mealId, categoryId)
        if (!mealCategoryRepository.existsById(id)) {
            throw Exception("MealCategory not found with id: $id")
        }
        mealCategoryRepository.deleteById(id)
    }

    fun delete(ids: List<MealCategoryId>) {
        mealCategoryRepository.deleteAllById(ids)
    }

    fun deleteAll() {
        mealCategoryRepository.deleteAll()
    }
}
