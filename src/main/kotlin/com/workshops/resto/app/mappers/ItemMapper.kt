package com.workshops.resto.app.mappers

import com.workshops.resto.app.dtos.ItemDto
import com.workshops.resto.data.entities.Category
import com.workshops.resto.data.entities.Item
import com.workshops.resto.util.ModelMapper
import org.springframework.stereotype.Component

@Component
class ItemMapper : ModelMapper<Item, ItemDto> {

    override fun toLayer(domain: Item): ItemDto =
        with(domain) {
            ItemDto(
                id = id,
                name = name,
                description = description,
                price = price,
                categoryId = category?.id
            )
        }

    override fun toDomain(layer: ItemDto): Item =
        with(layer) {
            Item(
                id = id,
                name = name,
                description = description,
                price = price,
                // The full category entity will be fetched and set in the service
                category = categoryId?.let { Category(id = it) }
            )
        }
}
