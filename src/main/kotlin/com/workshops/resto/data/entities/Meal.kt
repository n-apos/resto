package com.workshops.resto.data.entities

import jakarta.persistence.*
import jakarta.persistence.Table
import org.hibernate.annotations.DynamicUpdate
import java.util.*

@Entity
@DynamicUpdate
@Table(name = "meals")
data class Meal(
    @Id
    @GeneratedValue
    @Column(name = "id")
    var id: UUID? = null,

    @Column(name = "name", unique = true, nullable = false)
    var name: String = "",

    @Column(name = "description")
    var description: String = "",

    @OneToMany(
        mappedBy = "meal",
        cascade = [CascadeType.ALL],
        orphanRemoval = true,
    )
    var categories: MutableSet<MealCategory> = mutableSetOf()
)
