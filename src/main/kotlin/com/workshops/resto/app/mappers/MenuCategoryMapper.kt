package com.workshops.resto.app.mappers

import com.workshops.resto.app.dtos.MenuCategoryDto
import com.workshops.resto.data.entities.*
import com.workshops.resto.data.entities.embeddables.MenuCategoryId
import com.workshops.resto.util.ModelMapper
import org.springframework.stereotype.Component

@Component
class MenuCategoryMapper : ModelMapper<MenuCategory, MenuCategoryDto> {

    override fun toLayer(domain: MenuCategory): MenuCategoryDto =
        with(domain) {
            MenuCategoryDto(
                menuId = menu?.id!!,
                categoryId = category?.id!!,
                maxItems = maxItems,
                // Map the set of MenuCategoryItem entities to a list of item IDs
                itemIds = items.mapNotNull { it.item?.id }
            )
        }

    override fun toDomain(layer: MenuCategoryDto): MenuCategory =
        with(layer) {
            val menuCategory = MenuCategory(
                id = MenuCategoryId(menuId, categoryId),
                menu = Menu(id = menuId),
                category = Category(id = categoryId),
                maxItems = maxItems
            )
            // The service will be responsible for fetching the full Item entities
            // and creating the final MenuCategoryItem join entities.
            val menuCategoryItems = itemIds.map {
                MenuCategoryItem(
                    menuCategory = menuCategory,
                    item = Item(id = it)
                )
            }.toMutableSet()
            menuCategory.items = menuCategoryItems
            menuCategory
        }
}
