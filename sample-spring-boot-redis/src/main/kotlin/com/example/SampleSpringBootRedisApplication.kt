package com.example

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication

@SpringBootApplication
class SampleSpringBootRedisApplication

fun main(args: Array<String>) {
    runApplication<SampleSpringBootRedisApplication>(*args)
}
