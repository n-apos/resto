package com.workshops.resto.app.dtos

import com.fasterxml.jackson.annotation.JsonProperty
import java.util.UUID

data class TableDto(
    @param:JsonProperty("id")
    val id: UUID?,

    @param:JsonProperty("number")
    val number: Int
)
