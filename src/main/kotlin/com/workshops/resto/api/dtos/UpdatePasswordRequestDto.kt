package com.workshops.resto.api.dtos

import com.fasterxml.jackson.annotation.JsonProperty

data class UpdatePasswordRequestDto(
    @param:JsonProperty("old_password")
    val oldPassword: String,
    @param:JsonProperty("new_password")
    val newPassword: String
)
