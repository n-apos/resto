package com.workshops.resto.app.services

import com.workshops.resto.app.dtos.UserDto
import com.workshops.resto.app.mappers.UserMapper
import com.workshops.resto.data.repositories.UserRepository
import org.springframework.security.core.context.SecurityContextHolder
import org.springframework.security.core.userdetails.UserDetails
import org.springframework.stereotype.Service
import java.util.*

@Service
class UserService(
    private val userRepository: UserRepository,
    private val userMapper: UserMapper,
) {

    /**
     * Retrieves a list of all users.
     * @return A list of UserDto objects.
     */
    fun getAll(): List<UserDto> =
        userRepository.findAll()
            .map(userMapper::toLayer)

    fun getAllAdmins(): List<UserDto> =
        userRepository.getAdmins()
            .map(userMapper::toLayer)

    fun getById(id: UUID): UserDto =
        userRepository.findById(id)
            .orElseThrow { Exception("User not found with id: $id") }
            .let(userMapper::toLayer)


    fun getMe(): UserDto {
        val details = (SecurityContextHolder.getContext().authentication.principal as UserDetails)
        return userRepository.findByUsername(details.username)
            .getOrThrow()
            .let(userMapper::toLayer)
    }

    /**
     * Updates an existing user's information.
     * @param userDto The DTO containing the new details.
     * @return The updated UserDto.
     * @throws Exception if the user is not found.
     */
    fun update(userDto: UserDto): UserDto {
        val user = userMapper.toDomain(userDto)

        val savedUser = userRepository.save(user)

        return userMapper.toLayer(savedUser)
    }

    /**
     * Deletes a user by their ID.
     * @param id The ID of the user to delete.
     */
    fun delete(id: UUID) {
        if (!userRepository.existsById(id)) {
            throw Exception("User not found with id: $id")
        }
        userRepository.deleteById(id)
    }

    /**
     * Deletes a list of users by their IDs.
     * @param ids The list of user IDs to delete.
     */
    fun delete(ids: List<UUID>) {
        userRepository.deleteAllById(ids)
    }

    /**
     * Deletes all users.
     */
    fun deleteAll() {
        userRepository.deleteAll()
    }
}
