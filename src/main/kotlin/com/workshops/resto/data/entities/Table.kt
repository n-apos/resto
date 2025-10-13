package com.workshops.resto.data.entities

import jakarta.persistence.Column
import jakarta.persistence.Entity
import jakarta.persistence.GeneratedValue
import jakarta.persistence.Id
import org.hibernate.annotations.DynamicUpdate
import java.util.*

@Entity
@DynamicUpdate
@jakarta.persistence.Table(name = "tables")
data class Table(
    @Id
    @GeneratedValue
    @Column(name = "id")
    var id: UUID? = null,
    @Column(name = "number", unique = true, nullable = false)
    var number: Int = 0
)
