package com.workshops.resto.data.entities

import jakarta.persistence.*
import jakarta.persistence.Table
import org.hibernate.annotations.DynamicUpdate
import java.util.*

@Entity
@DynamicUpdate
@Table(name = "order_menus")
data class OrderMenu(
    @Id
    @GeneratedValue
    @Column(name = "id")
    var id: UUID? = null,

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "order_id", nullable = false)
    var order: Order? = null,

    @Column("menu_id")
    var menuId: UUID? = null,
    @Column("category_id")
    var categoryId: UUID? = null,
    @Column("item_id")
    var itemId: UUID? = null,


    @OneToMany
    @JoinColumns(
        JoinColumn(name = "menu_id", referencedColumnName = "menu_id"),
        JoinColumn(name = "category_id", referencedColumnName = "category_id"),
        JoinColumn(name = "item_id", referencedColumnName = "item_id"),
    )
    var items: MutableList<MenuCategoryItem> = mutableListOf(),

    @Column(name = "quantity", nullable = false)
    var quantity: Int = 0
)
