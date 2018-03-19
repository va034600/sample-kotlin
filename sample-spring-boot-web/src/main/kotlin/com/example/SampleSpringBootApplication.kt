package com.example

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication

@SpringBootApplication
class SampleSpringBootApplication

fun main(args: Array<String>) {
    runApplication<SampleSpringBootApplication>(*args)
}
