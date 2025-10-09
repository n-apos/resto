package com.workshops.resto.data.entities

import com.workshops.resto.data.entities.embeddables.MenuCategoryItemId
import jakarta.persistence.Column
import jakarta.persistence.EmbeddedId
import jakarta.persistence.Entity
import jakarta.persistence.FetchType
import jakarta.persistence.JoinColumn
import jakarta.persistence.JoinColumns
import jakarta.persistence.ManyToOne
import jakarta.persistence.MapsId
import jakarta.persistence.Table
import org.hibernate.annotations.DynamicUpdate

@Entity
@DynamicUpdate
@Table(name = "menu_category_items")
data class MenuCategoryItem(

    @EmbeddedId
    var id: MenuCategoryItemId = MenuCategoryItemId(),

    @ManyToOne(fetch = FetchType.LAZY)
    @MapsId("itemId") // Maps to the 'itemId' field in MenuCategoryItemId
    @JoinColumn(name = "item_id")
    var item: Item? = null,

    @ManyToOne(fetch = FetchType.LAZY)
    @MapsId("menuCategoryId") // Maps to the 'menuCategoryId' embeddable in MenuCategoryItemId
    @JoinColumns(
        JoinColumn(name = "menu_id", referencedColumnName = "menu_id"),
        JoinColumn(name = "category_id", referencedColumnName = "category_id")
    )
    var menuCategory: MenuCategory? = null,

    @Column(name = "additional_price", nullable = false)
    var additionalPrice: Double = 0.0,

)
