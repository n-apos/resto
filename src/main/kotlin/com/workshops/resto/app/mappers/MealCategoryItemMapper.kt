package com.workshops.resto.app.mappers

import com.workshops.resto.app.dtos.MealCategoryItemDto
import com.workshops.resto.data.entities.Item
import com.workshops.resto.data.entities.MealCategory
import com.workshops.resto.data.entities.MealCategoryItem
import com.workshops.resto.data.entities.embeddables.MealCategoryId
import com.workshops.resto.data.entities.embeddables.MealCategoryItemId
import com.workshops.resto.util.ModelMapper
import org.springframework.stereotype.Component

@Component
class MealCategoryItemMapper : ModelMapper<MealCategoryItem, MealCategoryItemDto> {

    override fun toLayer(domain: MealCategoryItem): MealCategoryItemDto =
        with(domain) {
            MealCategoryItemDto(
                mealId = id.mealCategoryId?.mealId!!,
                categoryId = id.mealCategoryId?.categoryId!!,
                itemId = id.itemId!!,
                additionalPrice = additionalPrice
            )
        }

    override fun toDomain(layer: MealCategoryItemDto): MealCategoryItem =
        with(layer) {
            val mealCategoryId = MealCategoryId(mealId, categoryId)
            val mealCategoryItemId = MealCategoryItemId(itemId, mealCategoryId)
            MealCategoryItem(
                id = mealCategoryItemId,
                item = Item(id = itemId),
                mealCategory = MealCategory(id = mealCategoryId),
                additionalPrice = additionalPrice
            )
        }
}
