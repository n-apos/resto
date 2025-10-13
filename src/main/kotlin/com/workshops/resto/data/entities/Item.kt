package com.workshops.resto.data.entities

import jakarta.persistence.*
import jakarta.persistence.Table
import org.hibernate.annotations.DynamicUpdate
import java.util.*

@Entity
@DynamicUpdate
@Table(name = "items")
data class Item(
    @Id
    @GeneratedValue
    @Column(name = "id")
    var id: UUID? = null,

    @Column(name = "name", unique = true, nullable = false)
    var name: String = "",

    @Column(name = "description")
    var description: String = "",

    @Column(name = "price", nullable = false)
    var price: Double = 0.0,

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "category_id", nullable = false)
    var category: Category? = null
)
