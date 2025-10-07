package com.workshops.resto.services

import com.workshops.resto.data.entities.User
import com.workshops.resto.data.repositories.UserRepository
import org.springframework.stereotype.Service
import java.util.UUID

@Service
class UserService(private val userRepository: UserRepository) {

    /**
     * Retrieves a list of all users.
     * @return A list of User entities.
     */
    fun getAll(): List<User> {
        return userRepository.findAll()
    }

    /**
     * Updates an existing user's information.
     * @param id The ID of the user to update.
     * @param userDetails The User object containing the new details.
     * @return The updated User entity.
     * @throws Exception if the user is not found.
     */
    fun update(id: UUID, userDetails: User): User {
        val existingUser = userRepository.findById(id)
            .orElseThrow { Exception("User not found with id: $id") }

        val updatedUser = existingUser.copy(
            firstname = userDetails.firstname,
            lastname = userDetails.lastname,
            roles = userDetails.roles
        )

        return userRepository.save(updatedUser)
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
