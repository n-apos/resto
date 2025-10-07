package com.workshops.resto.data.entities

import com.fasterxml.jackson.annotation.JsonIgnore
import jakarta.persistence.Column
import jakarta.persistence.Entity
import jakarta.persistence.FetchType
import jakarta.persistence.GeneratedValue
import jakarta.persistence.Id
import jakarta.persistence.JoinColumn
import jakarta.persistence.ManyToOne
import jakarta.persistence.Table
import org.hibernate.annotations.DynamicUpdate
import java.util.UUID

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
    @JoinColumn(name = "menu_id")
    var menu: Menu? = null
)
