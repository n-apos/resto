package com.napos.khelles.com.workshops.resto.data.entities

import jakarta.persistence.*
import org.hibernate.annotations.DynamicUpdate
import java.util.*

@Entity
@DynamicUpdate
@Table(name = "items")
data class Item(
    @Id
    @GeneratedValue
    @Column("id")
    val id: UUID? = null,
    @Column("name")
    val name: String = "",
    @Column("description")
    val description: String = "",
    @Column("price")
    val price: Double = 0.0,
)