package com.workshops.resto.data.entities

import jakarta.persistence.*
import jakarta.persistence.Table
import org.hibernate.annotations.DynamicUpdate
import java.util.UUID

@Entity
@DynamicUpdate
@Table(name = "order_meals")
data class OrderMeal(
    @Id
    @GeneratedValue
    @Column(name = "id")
    var id: UUID? = null,

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "order_id", nullable = false)
    var order: Order? = null,

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "meal_id", nullable = false)
    var meal: Meal? = null,

    // We will need a separate entity to represent the chosen items for this specific ordered meal.
    // For now, we keep the entity simple.

    @Column(name = "quantity", nullable = false)
    var quantity: Int = 1
)
