package com.workshops.resto.data.entities

import com.workshops.resto.data.entities.embeddables.MenuCategoryId
import jakarta.persistence.Column
import jakarta.persistence.EmbeddedId
import jakarta.persistence.Entity
import jakarta.persistence.FetchType
import jakarta.persistence.JoinColumn
import jakarta.persistence.ManyToOne
import jakarta.persistence.MapsId
import jakarta.persistence.Table
import org.hibernate.annotations.DynamicUpdate

@Entity
@DynamicUpdate
@Table(name = "menu_categories")
data class MenuCategory(
    @EmbeddedId
    var id: MenuCategoryId = MenuCategoryId(),
    @ManyToOne(fetch = FetchType.LAZY)
    @MapsId("menuId")
    @JoinColumn(name = "menu_id")
    var menu: Menu? = null,
    @ManyToOne(fetch = FetchType.LAZY)
    @MapsId("categoryId")
    @JoinColumn(name = "category_id")
    var category: Category? = null,
    @Column(name = "max_items")
    var maxItems: Int = 0
)
