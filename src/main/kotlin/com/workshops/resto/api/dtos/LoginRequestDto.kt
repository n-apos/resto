package com.workshops.resto.api.dtos

import com.fasterxml.jackson.annotation.JsonProperty

data class LoginRequestDto(
    @param:JsonProperty("username")
    val username: String,
    @param:JsonProperty("password")
    val password: String
)
