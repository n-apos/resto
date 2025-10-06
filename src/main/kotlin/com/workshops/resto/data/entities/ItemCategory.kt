package com.workshops.resto.data.entities

import jakarta.persistence.*
import org.hibernate.annotations.DynamicUpdate
import java.util.*

@Entity
@DynamicUpdate
@Table(name = "item_categories")
data class ItemCategory(
    @Id
    @GeneratedValue
    @Column("id")
    val id: UUID? = null,
    @Column("name")
    val name: String = "",
    @Column("description")
    val description: String = "",
)
