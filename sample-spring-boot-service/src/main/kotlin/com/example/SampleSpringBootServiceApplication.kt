package com.example

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication

@SpringBootApplication
class SampleSpringBootServiceApplication

fun main(args: Array<String>) {
    runApplication<SampleSpringBootServiceApplication>(*args)
}
