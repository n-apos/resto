package com.workshops.resto.data.entities.embeddables

import jakarta.persistence.Column
import jakarta.persistence.Embeddable
import java.io.Serializable
import java.util.*

@Embeddable
data class OrderMenuItemId(
    @Column("order_menu_id")
    var orderMenuId: UUID? = null,
    @Column("menu_category_item_id")
    var menuCategoryItemId: UUID? = null
) : Serializable
