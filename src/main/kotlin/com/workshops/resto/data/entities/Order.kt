package com.workshops.resto.data.entities

import jakarta.persistence.*
import org.hibernate.annotations.DynamicUpdate
import java.util.*
import jakarta.persistence.Table as PersistenceTable

@Entity
@DynamicUpdate
@PersistenceTable(name = "orders")
data class Order(
    @Id
    @GeneratedValue
    @Column(name = "id")
    var id: UUID? = null,
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id", nullable = false)
    var user: User? = null,
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "table_id", nullable = false)
    var table: Table? = null,
    @OneToMany(mappedBy = "order", cascade = [CascadeType.ALL], orphanRemoval = true)
    var items: MutableList<OrderItem> = mutableListOf()
)
