package com.workshops.resto.data.entities

import com.workshops.resto.data.entities.embeddables.MealCategoryItemId
import jakarta.persistence.*
import jakarta.persistence.Table
import org.hibernate.annotations.DynamicUpdate

@Entity
@DynamicUpdate
@Table(name = "meal_category_items")
data class MealCategoryItem(

    @EmbeddedId
    var id: MealCategoryItemId = MealCategoryItemId(),

    @ManyToOne(fetch = FetchType.LAZY)
    @MapsId("itemId")
    @JoinColumn(name = "item_id")
    var item: Item? = null,

    @ManyToOne(fetch = FetchType.LAZY)
    @MapsId("mealCategoryId")
    @JoinColumns(
        JoinColumn(name = "meal_id", referencedColumnName = "meal_id"),
        JoinColumn(name = "category_id", referencedColumnName = "category_id")
    )
    var mealCategory: MealCategory? = null,

    @Column(name = "additional_price", nullable = false)
    var additionalPrice: Double = 0.0
)
