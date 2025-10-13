package com.workshops.resto.data.entities

import jakarta.persistence.*
import jakarta.persistence.Table
import org.hibernate.annotations.DynamicUpdate
import java.util.*

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
