package com.example.receiver

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication

@SpringBootApplication
class SampleSpringJmsSqsApplication

fun main(args: Array<String>) {
    runApplication<SampleSpringJmsSqsApplication>(*args)
}
