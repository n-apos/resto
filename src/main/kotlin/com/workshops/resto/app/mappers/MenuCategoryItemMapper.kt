package com.workshops.resto.app.mappers

import com.workshops.resto.app.dtos.MenuCategoryItemDto
import com.workshops.resto.data.entities.Item
import com.workshops.resto.data.entities.MenuCategory
import com.workshops.resto.data.entities.MenuCategoryItem
import com.workshops.resto.data.entities.embeddables.MenuCategoryId
import com.workshops.resto.data.entities.embeddables.MenuCategoryItemId
import com.workshops.resto.util.ModelMapper
import org.springframework.stereotype.Component

@Component
class MenuCategoryItemMapper : ModelMapper<MenuCategoryItem, MenuCategoryItemDto> {

    override fun toLayer(domain: MenuCategoryItem): MenuCategoryItemDto =
        with(domain) {
            MenuCategoryItemDto(
                menuId = id.menuCategoryId?.menuId!!,
                categoryId = id.menuCategoryId?.categoryId!!,
                itemId = id.itemId!!,
                additionalPrice = additionalPrice
            )
        }

    override fun toDomain(layer: MenuCategoryItemDto): MenuCategoryItem =
        with(layer) {
            val menuCategoryId = MenuCategoryId(menuId, categoryId)
            val menuCategoryItemId = MenuCategoryItemId(itemId, menuCategoryId)
            MenuCategoryItem(
                id = menuCategoryItemId,
                item = Item(id = itemId),
                menuCategory = MenuCategory(id = menuCategoryId),
                additionalPrice = additionalPrice
            )
        }
}
