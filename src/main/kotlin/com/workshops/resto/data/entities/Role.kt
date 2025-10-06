package com.workshops.resto.data.entities

import com.fasterxml.jackson.annotation.JsonIgnore
import jakarta.persistence.Column
import jakarta.persistence.Entity
import jakarta.persistence.GeneratedValue
import jakarta.persistence.Id
import jakarta.persistence.ManyToMany
import jakarta.persistence.Table
import org.hibernate.annotations.DynamicUpdate
import java.util.UUID

@Entity
@DynamicUpdate
@Table(name = "roles")
data class Role(
    @Id
    @GeneratedValue
    @Column(name = "id")
    var id: UUID? = null,

    @Column(name = "name")
    var name: String = "",

    @ManyToMany(mappedBy = "roles")
    @JsonIgnore
    var users: MutableList<User> = mutableListOf(),
)
