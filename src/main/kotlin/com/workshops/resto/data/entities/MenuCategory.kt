package com.workshops.resto.data.entities

import com.workshops.resto.data.entities.embeddables.MenuCategoryId
import jakarta.persistence.*
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
    var maxItems: Int = 0,

    @OneToMany(mappedBy = "menuCategory", cascade = [CascadeType.ALL], orphanRemoval = true)
    var items: MutableSet<MenuCategoryItem> = mutableSetOf()
)
