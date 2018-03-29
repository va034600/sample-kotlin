package com.example

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication

@SpringBootApplication
class SampleSpringBootRedis2Application

fun main(args: Array<String>) {
    runApplication<SampleSpringBootRedis2Application>(*args)
}
