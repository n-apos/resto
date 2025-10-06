package com.workshops.resto.data.entities

import com.fasterxml.jackson.annotation.JsonIgnore
import jakarta.persistence.Column
import jakarta.persistence.Entity
import jakarta.persistence.FetchType
import jakarta.persistence.GeneratedValue
import jakarta.persistence.Id
import jakarta.persistence.JoinColumn
import jakarta.persistence.JoinTable
import jakarta.persistence.ManyToMany
import jakarta.persistence.Table
import org.hibernate.annotations.DynamicUpdate
import java.util.UUID

@Entity
@DynamicUpdate
@Table(name = "users")
data class User(
    @Id
    @GeneratedValue
    @Column(name = "id")
    var id: UUID? = null,

    @Column(name = "firstname")
    var firstname: String = "",

    @Column(name = "lastname")
    var lastname: String = "",

    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(
        name = "user_roles",
        joinColumns = [JoinColumn(name = "user_id")],
        inverseJoinColumns = [JoinColumn(name = "role_id")]
    )
    var roles: MutableSet<Role> = mutableSetOf()
)
