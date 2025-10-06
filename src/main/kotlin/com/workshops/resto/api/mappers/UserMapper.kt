package com.workshops.resto.api.mappers

import com.workshops.resto.api.dtos.UserDto
import com.workshops.resto.data.entities.User
import com.workshops.resto.util.ModelMapper
import org.springframework.stereotype.Component

@Component
class UserMapper : ModelMapper<User, UserDto> {

    override fun toLayer(domain: User): UserDto =
        with(domain) {
            UserDto(
                id = id!!,
                firstName = firstname,
                lastName = lastname,
                roles = roles.map { it.name },
            )
        }

    override fun toDomain(layer: UserDto): User {
        throw UnsupportedOperationException("Not implemented")
    }

}
