package com.waproject.watest.model

import java.time.LocalDateTime
import javax.persistence.*

@Entity
@Table(name = "users")
class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    var id: Int? = null
    var name: String? = null
    var document: String? = null
    var createdAt: LocalDateTime? = null
    var updatedAt: LocalDateTime? = null

    @PrePersist
    fun prePersist() {
        createdAt = LocalDateTime.now();
    }

    @PreUpdate
    fun preUpdate() {
        updatedAt = LocalDateTime.now();
    }

}


