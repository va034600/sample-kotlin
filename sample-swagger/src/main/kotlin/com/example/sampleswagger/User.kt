package com.example.sampleswagger

import java.io.Serializable
import javax.persistence.*

@Entity
data class User(
        @Id
        @GeneratedValue
        var id: Long = 0,

        @Column
        var name: String = ""
):Serializable
