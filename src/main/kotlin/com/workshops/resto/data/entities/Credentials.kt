package com.napos.khelles.com.workshops.resto.data.entities

import jakarta.persistence.CascadeType
import jakarta.persistence.Column
import jakarta.persistence.Entity
import jakarta.persistence.GeneratedValue
import jakarta.persistence.Id
import jakarta.persistence.OneToOne
import jakarta.persistence.Table
import org.hibernate.annotations.DynamicUpdate
import java.util.UUID


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