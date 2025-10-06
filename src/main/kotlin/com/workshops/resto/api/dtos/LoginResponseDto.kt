package com.workshops.resto.api.dtos

import com.fasterxml.jackson.annotation.JsonProperty

data class LoginResponseDto(
    @param:JsonProperty("token")
    val token: String
)
