package com.workshops.resto.app.mappers

import com.workshops.resto.app.dtos.MenuCategorySpecDto
import com.workshops.resto.app.dtos.MenuDto
import com.workshops.resto.data.entities.Category
import com.workshops.resto.data.entities.Menu
import com.workshops.resto.data.entities.MenuCategory
import com.workshops.resto.util.ModelMapper
import org.springframework.stereotype.Component

@Component
class MenuMapper : ModelMapper<Menu, MenuDto> {

    override fun toLayer(domain: Menu): MenuDto =
        with(domain) {
            MenuDto(
                id = id,
                name = name,
                description = description,
                categories = categories.map {
                    MenuCategorySpecDto(
                        categoryId = it.category?.id!!,
                        maxItems = it.maxItems
                    )
                }
            )
        }

    override fun toDomain(layer: MenuDto): Menu =
        with(layer) {
            val menu = Menu(
                id = id,
                name = name,
                description = description
            )
            // The service will be responsible for fetching the full category entities
            // and creating the MenuCategory join entities.
            val menuCategories = categories.map {
                MenuCategory(
                    menu = menu,
                    category = Category(id = it.categoryId),
                    maxItems = it.maxItems
                )
            }.toMutableSet()
            menu.categories = menuCategories
            menu
        }
}
