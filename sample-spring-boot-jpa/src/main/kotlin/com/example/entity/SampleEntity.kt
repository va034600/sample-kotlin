package com.example.entity

import java.io.Serializable
import java.time.LocalDateTime
import javax.persistence.*

@Entity
@Table(name = "sample")
data class SampleEntity(
        @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
        var id: Int = 0,

        @Column
        var message: String? = null,

        @Column(nullable = false)
        var updated: LocalDateTime = LocalDateTime.now()
): Serializable