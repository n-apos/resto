package com.workshops.resto.data.entities

import com.fasterxml.jackson.annotation.JsonIgnore
import jakarta.persistence.*
import jakarta.persistence.Table
import org.hibernate.annotations.DynamicUpdate
import java.util.*

@Entity
@DynamicUpdate
@Table(name = "roles")
data class Role(
    @Id
    @GeneratedValue
    @Column(name = "id")
    var id: UUID? = null,

    @Column(name = "name", unique = true)
    var name: String = "",

    @ManyToMany(mappedBy = "roles")
    @JsonIgnore
    var users: MutableList<User> = mutableListOf(),
)
