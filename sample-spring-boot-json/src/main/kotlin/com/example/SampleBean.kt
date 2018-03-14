package com.example

import java.io.Serializable
import java.time.LocalDateTime

class SampleBean (
    var id: Int,
    var name: String,
    var updated: LocalDateTime = LocalDateTime.now()
):Serializable
