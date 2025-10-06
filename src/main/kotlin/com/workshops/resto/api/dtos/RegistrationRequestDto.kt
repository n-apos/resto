package com.workshops.resto.api.dtos

import com.fasterxml.jackson.annotation.JsonProperty

data class RegistrationRequestDto(
    @param:JsonProperty("firstname")
    val firstname: String,
    @param:JsonProperty("lastname")
    val lastname: String,
    @param:JsonProperty("username")
    val username: String,
    @param:JsonProperty("password")
    val password: String
)
