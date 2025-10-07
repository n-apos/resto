package com.workshops.resto.data.entities

import jakarta.persistence.Column
import jakarta.persistence.Entity
import jakarta.persistence.FetchType
import jakarta.persistence.GeneratedValue
import jakarta.persistence.Id
import jakarta.persistence.JoinColumn
import jakarta.persistence.JoinTable
import jakarta.persistence.ManyToMany
import jakarta.persistence.Table
import org.hibernate.annotations.DynamicUpdate
import java.util.UUID

@Entity
@DynamicUpdate
@Table(name = "menus")
data class Menu(
    @Id
    @GeneratedValue
    @Column(name = "id")
    var id: UUID? = null,

    @Column(name = "name", unique = true, nullable = false)
    var name: String = "",

    @Column(name = "description")
    var description: String = "",

    @ManyToMany(fetch = FetchType.LAZY)
    @JoinTable(
        name = "menu_categories",
        joinColumns = [JoinColumn(name = "menu_id")],
        inverseJoinColumns = [JoinColumn(name = "category_id")]
    )
    var categories: MutableSet<Category> = mutableSetOf()
)
