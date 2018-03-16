package com.example

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication

@SpringBootApplication
class SampleSpringBootJpaApplication

fun main(args: Array<String>) {
    runApplication<SampleSpringBootJpaApplication>(*args)
}
