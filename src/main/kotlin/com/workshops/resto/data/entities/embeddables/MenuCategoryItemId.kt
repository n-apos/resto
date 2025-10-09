package com.workshops.resto.data.entities.embeddables

import jakarta.persistence.AttributeOverride
import jakarta.persistence.AttributeOverrides
import jakarta.persistence.Column
import jakarta.persistence.Embeddable
import java.io.Serializable
import java.util.UUID

@Embeddable
data class MenuCategoryItemId(

    @Column(name = "item_id")
    var itemId: UUID? = null,

    // This is an embedded composite foreign key, we override the column names
    // to match the table structure of 'menu_category_items'
    @AttributeOverrides(
        AttributeOverride(name = "menuId", column = Column(name = "menu_id")),
        AttributeOverride(name = "categoryId", column = Column(name = "category_id"))
    )
    var menuCategoryId: MenuCategoryId? = MenuCategoryId()

) : Serializable
