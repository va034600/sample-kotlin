package com.example.sender

import org.springframework.boot.CommandLineRunner
import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication
import org.springframework.context.annotation.Bean

@SpringBootApplication
class SampleSpringJmsSqsApplication(val sender:Sender){
    @Bean
    fun init() = CommandLineRunner {
        sender.send("送ったデータ")
    }
}

fun main(args: Array<String>) {
    runApplication<SampleSpringJmsSqsApplication>(*args)
}
