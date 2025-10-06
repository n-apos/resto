package com.workshops.resto.data.entities

import jakarta.persistence.*
import org.hibernate.annotations.DynamicUpdate
import java.util.*


@Entity
@DynamicUpdate
@Table(name = "credentials")
data class Credentials(
    @Id
    @GeneratedValue
    @Column("id")
    val id: UUID? = null,
    @Column("username")
    val username: String = "",
    @Column("password")
    val password: String = "",
    @OneToOne(cascade = [CascadeType.ALL])
    val user: User? = null,
)