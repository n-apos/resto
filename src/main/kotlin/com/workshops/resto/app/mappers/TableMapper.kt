package com.workshops.resto.app.mappers

import com.workshops.resto.app.dtos.TableDto
import com.workshops.resto.data.entities.Table
import com.workshops.resto.util.ModelMapper
import org.springframework.stereotype.Component

@Component
class TableMapper : ModelMapper<Table, TableDto> {

    override fun toLayer(domain: Table): TableDto =
        with(domain) {
            TableDto(
                id = id,
                number = number
            )
        }

    override fun toDomain(layer: TableDto): Table =
        with(layer) {
            Table(
                id = id,
                number = number
            )
        }
}
