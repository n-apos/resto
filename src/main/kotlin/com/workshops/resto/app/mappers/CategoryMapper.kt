package com.workshops.resto.app.mappers

import com.workshops.resto.app.dtos.CategoryDto
import com.workshops.resto.data.entities.Category
import com.workshops.resto.util.ModelMapper
import org.springframework.stereotype.Component

@Component
class CategoryMapper: ModelMapper<Category, CategoryDto> {

    override fun toLayer(domain: Category): CategoryDto =
        with(domain) {
            CategoryDto(
                id = id,
                name = name,
                description = description,
            )
        }

    override fun toDomain(layer: CategoryDto): Category =
        with(layer) {
            Category(
                id = id,
                name = name,
                description = description,
            )
        }

}