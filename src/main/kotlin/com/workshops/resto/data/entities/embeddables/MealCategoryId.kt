package com.workshops.resto.data.entities.embeddables

import jakarta.persistence.Column
import jakarta.persistence.Embeddable
import java.io.Serializable
import java.util.UUID

@Embeddable
data class MealCategoryId(
    @Column(name = "meal_id")
    var mealId: UUID? = null,

    @Column(name = "category_id")
    var categoryId: UUID? = null
) : Serializable
