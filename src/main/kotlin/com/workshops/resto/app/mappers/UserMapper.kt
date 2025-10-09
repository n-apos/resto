package com.workshops.resto.app.mappers

import com.workshops.resto.app.dtos.UserDto
import com.workshops.resto.data.entities.User
import com.workshops.resto.util.ModelMapper
import org.springframework.stereotype.Component

@Component
class UserMapper : ModelMapper<User, UserDto> {

    override fun toLayer(domain: User): UserDto {
        return UserDto(
            id = domain.id,
            firstName = domain.firstname,
            lastName = domain.lastname,
            roles = domain.roles.map { it.name }.toSet()
        )
    }

    override fun toDomain(layer: UserDto): User {
        // Note: When mapping from DTO to entity, we don't map roles
        // to prevent potential security issues. Role management should be a separate, explicit process.
        return User(
            id = layer.id,
            firstname = layer.firstName,
            lastname = layer.lastName
        )
    }
}
