package com.example

import org.springframework.boot.SpringApplication
import org.springframework.boot.autoconfigure.SpringBootApplication

@SpringBootApplication
class SampleSpringBootRedis2Application

fun main(args: Array<String>) {
    SpringApplication.run(SampleSpringBootRedis2Application::class.java, *args)
}
