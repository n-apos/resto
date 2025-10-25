package com.workshops.resto.data.entities

import com.fasterxml.jackson.annotation.JsonIgnore
import jakarta.persistence.*
import jakarta.persistence.Table
import org.hibernate.annotations.DynamicUpdate
import java.util.*

@Entity
@DynamicUpdate
@Table(name = "order_items")
data class OrderItem(
    @Id
    @GeneratedValue
    @Column(name = "id")
    var id: UUID? = null,

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "order_id", nullable = false)
    @JsonIgnore // Prevents infinite recursion during serialization
    var order: Order? = null,

    @ManyToOne
    @JoinColumn(name = "item_id")
    var item: Item? = null,

    @ManyToOne
    @JoinColumn(name = "meal_id")
    var meal: Meal? = null
)
