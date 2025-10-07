package com.workshops.resto.services

import com.workshops.resto.data.entities.Category
import com.workshops.resto.data.repositories.CategoryRepository
import org.springframework.stereotype.Service
import java.util.UUID

@Service
class CategoryService(private val categoryRepository: CategoryRepository) {

    fun create(category: Category): Category {
        return categoryRepository.save(category)
    }

    fun update(id: UUID, categoryDetails: Category): Category {
        val existingCategory = categoryRepository.findById(id)
            .orElseThrow { Exception("Category not found with id: $id") }

        val updatedCategory = existingCategory.copy(
            name = categoryDetails.name,
            description = categoryDetails.description
        )

        return categoryRepository.save(updatedCategory)
    }

    fun getAll(): List<Category> {
        return categoryRepository.findAll()
    }

    fun getById(id: UUID): Category {
        return categoryRepository.findById(id)
            .orElseThrow { Exception("Category not found with id: $id") }
    }

    fun delete(id: UUID) {
        if (!categoryRepository.existsById(id)) {
            throw Exception("Category not found with id: $id")
        }
        categoryRepository.deleteById(id)
    }

    fun delete(ids: List<UUID>) {
        categoryRepository.deleteAllById(ids)
    }

    fun deleteAll() {
        categoryRepository.deleteAll()
    }
}
