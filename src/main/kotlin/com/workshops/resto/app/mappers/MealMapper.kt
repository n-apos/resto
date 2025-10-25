package com.workshops.resto.app.mappers

import com.workshops.resto.app.dtos.MealCategorySpecDto
import com.workshops.resto.app.dtos.MealDto
import com.workshops.resto.data.entities.Category
import com.workshops.resto.data.entities.Meal
import com.workshops.resto.data.entities.MealCategory
import com.workshops.resto.util.ModelMapper
import org.springframework.stereotype.Component

@Component
class MealMapper : ModelMapper<Meal, MealDto> {

    override fun toLayer(domain: Meal): MealDto =
        with(domain) {
            MealDto(
                id = id,
                name = name,
                description = description,
                categories = categories.map {
                    MealCategorySpecDto(
                        categoryId = it.category?.id!!,
                        maxItems = it.maxItems
                    )
                }
            )
        }

    override fun toDomain(layer: MealDto): Meal =
        with(layer) {
            val meal = Meal(
                id = id,
                name = name,
                description = description
            )
            val mealCategories = categories.map {
                MealCategory(
                    meal = meal,
                    category = Category(id = it.categoryId),
                    maxItems = it.maxItems
                )
            }.toMutableSet()
            meal.categories = mealCategories
            meal
        }
}
