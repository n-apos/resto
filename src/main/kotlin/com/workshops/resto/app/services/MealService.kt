package com.workshops.resto.app.services

import com.workshops.resto.app.dtos.MealDto
import com.workshops.resto.app.mappers.MealMapper
import com.workshops.resto.data.entities.MealCategory
import com.workshops.resto.data.repositories.CategoryRepository
import com.workshops.resto.data.repositories.MealRepository
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional
import java.util.UUID

@Service
class MealService(
    private val mealRepository: MealRepository,
    private val categoryRepository: CategoryRepository,
    private val mealMapper: MealMapper
) {

    @Transactional
    fun create(mealDto: MealDto): MealDto {
        val meal = mealMapper.toDomain(mealDto)

        meal.categories.forEach { mealCategory ->
            val category = categoryRepository.findById(mealCategory.category?.id!!)
                .orElseThrow { Exception("Category not found with id: ${mealCategory.category?.id}") }
            mealCategory.category = category
        }

        val savedMeal = mealRepository.save(meal)
        return mealMapper.toLayer(savedMeal)
    }

    @Transactional
    fun update(id: UUID, mealDto: MealDto): MealDto {
        val existingMeal = mealRepository.findById(id)
            .orElseThrow { Exception("Meal not found with id: $id") }

        existingMeal.name = mealDto.name
        existingMeal.description = mealDto.description

        existingMeal.categories.clear()
        mealDto.categories.forEach { specDto ->
            val category = categoryRepository.findById(specDto.categoryId)
                .orElseThrow { Exception("Category not found with id: ${specDto.categoryId}") }
            existingMeal.categories.add(
                MealCategory(
                    meal = existingMeal,
                    category = category,
                    maxItems = specDto.maxItems
                )
            )
        }

        val savedMeal = mealRepository.save(existingMeal)
        return mealMapper.toLayer(savedMeal)
    }

    fun getAll(): List<MealDto> {
        return mealRepository.findAll().map(mealMapper::toLayer)
    }

    fun getById(id: UUID): MealDto {
        val meal = mealRepository.findById(id)
            .orElseThrow { Exception("Meal not found with id: $id") }
        return mealMapper.toLayer(meal)
    }

    fun delete(id: UUID) {
        if (!mealRepository.existsById(id)) {
            throw Exception("Meal not found with id: $id")
        }
        mealRepository.deleteById(id)
    }

    fun delete(ids: List<UUID>) {
        mealRepository.deleteAllById(ids)
    }

    fun deleteAll() {
        mealRepository.deleteAll()
    }
}
