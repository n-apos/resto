package com.napos.khelles.com.workshops.resto.data.entities

import jakarta.persistence.*
import org.hibernate.annotations.DynamicUpdate
import java.util.*

@Entity
@DynamicUpdate
@Table(name = "users")
data class User(
    @Id
    @GeneratedValue
    @Column("id")
    val id: UUID? = null,
    @Column("firstname")
    val firstname: String = "",
    @Column("lastname")
    val lastname: String = "",
)
