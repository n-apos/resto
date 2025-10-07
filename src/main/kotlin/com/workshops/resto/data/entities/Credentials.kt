package com.workshops.resto.data.entities

import com.fasterxml.jackson.annotation.JsonIgnore
import jakarta.persistence.*
import jakarta.persistence.Table
import org.hibernate.annotations.DynamicUpdate
import java.util.*

@Entity
@DynamicUpdate
@Table(name = "credentials")
data class Credentials(
    @Id
    @GeneratedValue
    @Column(name = "id")
    var id: UUID? = null,
    @Column(name = "username", unique = true, nullable = false)
    var username: String = "",
    @Column(name = "password", nullable = false)
    @JsonIgnore // Never expose the password
    var password: String = "",
    @OneToOne(fetch = FetchType.LAZY, cascade = [CascadeType.ALL])
    @JoinColumn(name = "user_id", nullable = false)
    var user: User? = null
)
