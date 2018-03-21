package com.example

import org.springframework.boot.CommandLineRunner
import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication
import org.springframework.context.annotation.Bean

@SpringBootApplication
class SampleSpringBootCommandLineRunnerApplication{
    @Bean
    fun init() = CommandLineRunner {
        println("init")
    }
}

fun main(args: Array<String>) {
    runApplication<SampleSpringBootCommandLineRunnerApplication>(*args)
}
