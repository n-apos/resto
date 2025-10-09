package com.workshops.resto.app.mappers

import com.workshops.resto.app.dtos.MenuCategoryDto
import com.workshops.resto.data.entities.Category
import com.workshops.resto.data.entities.Menu
import com.workshops.resto.data.entities.MenuCategory
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
                maxItems = maxItems
            )
        }

    override fun toDomain(layer: MenuCategoryDto): MenuCategory =
        with(layer) {
            MenuCategory(
                id = MenuCategoryId(menuId, categoryId),
                menu = Menu(id = menuId),
                category = Category(id = categoryId),
                maxItems = maxItems
            )
        }
}
