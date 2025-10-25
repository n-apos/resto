package com.workshops.resto.data.entities.embeddables

import jakarta.persistence.AttributeOverride
import jakarta.persistence.AttributeOverrides
import jakarta.persistence.Column
import jakarta.persistence.Embeddable
import java.io.Serializable
import java.util.UUID

@Embeddable
data class MealCategoryItemId(

    @Column(name = "item_id")
    var itemId: UUID? = null,

    @AttributeOverrides(
        AttributeOverride(name = "mealId", column = Column(name = "meal_id")),
        AttributeOverride(name = "categoryId", column = Column(name = "category_id"))
    )
    var mealCategoryId: MealCategoryId? = MealCategoryId()

) : Serializable
