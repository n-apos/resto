package com.workshops.resto.app.dtos

import com.fasterxml.jackson.annotation.JsonProperty
import java.util.UUID

data class UserDto(
    @param:JsonProperty("id")
    val id: UUID?,

    @param:JsonProperty("first_name")
    val firstName: String,

    @param:JsonProperty("last_name")
    val lastName: String,

    @param:JsonProperty("roles")
    val roles: List<String>
)
