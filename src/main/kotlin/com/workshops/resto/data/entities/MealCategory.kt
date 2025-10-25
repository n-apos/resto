package com.workshops.resto.data.entities

import com.workshops.resto.data.entities.embeddables.MealCategoryId
import jakarta.persistence.*
import jakarta.persistence.Table
import org.hibernate.annotations.DynamicUpdate

@Entity
@DynamicUpdate
@Table(name = "meal_categories")
data class MealCategory(
    @EmbeddedId
    var id: MealCategoryId = MealCategoryId(),

    @ManyToOne(fetch = FetchType.LAZY)
    @MapsId("mealId")
    @JoinColumn(name = "meal_id")
    var meal: Meal? = null,

    @ManyToOne(fetch = FetchType.LAZY)
    @MapsId("categoryId")
    @JoinColumn(name = "category_id")
    var category: Category? = null,

    @Column(name = "max_items")
    var maxItems: Int = 0,

    @OneToMany(mappedBy = "mealCategory", cascade = [CascadeType.ALL], orphanRemoval = true)
    var items: MutableSet<MealCategoryItem> = mutableSetOf()
)
