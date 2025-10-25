package com.workshops.resto.app.mappers

import com.workshops.resto.app.dtos.MealCategoryDto
import com.workshops.resto.data.entities.Category
import com.workshops.resto.data.entities.Item
import com.workshops.resto.data.entities.Meal
import com.workshops.resto.data.entities.MealCategory
import com.workshops.resto.data.entities.MealCategoryItem
import com.workshops.resto.data.entities.embeddables.MealCategoryId
import com.workshops.resto.util.ModelMapper
import org.springframework.stereotype.Component

@Component
class MealCategoryMapper : ModelMapper<MealCategory, MealCategoryDto> {

    override fun toLayer(domain: MealCategory): MealCategoryDto =
        with(domain) {
            MealCategoryDto(
                mealId = meal?.id!!,
                categoryId = category?.id!!,
                maxItems = maxItems,
                itemIds = items.mapNotNull { it.item?.id }
            )
        }

    override fun toDomain(layer: MealCategoryDto): MealCategory =
        with(layer) {
            val mealCategory = MealCategory(
                id = MealCategoryId(mealId, categoryId),
                meal = Meal(id = mealId),
                category = Category(id = categoryId),
                maxItems = maxItems
            )
            val mealCategoryItems = itemIds.map {
                MealCategoryItem(
                    mealCategory = mealCategory,
                    item = Item(id = it)
                )
            }.toMutableSet()
            mealCategory.items = mealCategoryItems
            mealCategory
        }
}
