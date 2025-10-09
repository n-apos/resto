package com.workshops.resto.app.services

import com.workshops.resto.app.dtos.CategoryDto
import com.workshops.resto.app.mappers.CategoryMapper
import com.workshops.resto.data.repositories.CategoryRepository
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service
import java.util.*

@Service
class CategoryService @Autowired constructor(
    private val categoryRepository: CategoryRepository,
    private val categoryMapper: CategoryMapper,
) {

    fun create(categoryRequestDto: CategoryDto): CategoryDto {
        val categoryEntity = categoryMapper.toDomain(categoryRequestDto)
        val savedCategory = categoryRepository.save(categoryEntity)
        return categoryMapper.toLayer(savedCategory)
    }

    fun update(id: UUID, categoryRequestDto: CategoryDto): CategoryDto {
        val category = categoryMapper.toDomain(categoryRequestDto)

        val savedCategory = categoryRepository.save(category)

        return categoryMapper.toLayer(savedCategory)
    }

    fun getAll(): List<CategoryDto> =
        categoryRepository.findAll()
            .map(categoryMapper::toLayer)

    fun getById(id: UUID): CategoryDto {
        val category = categoryRepository.findById(id)
            .orElseThrow { Exception("Category not found with id: $id") }
        return categoryMapper.toLayer(category)
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
