package com.example.send

import org.springframework.boot.CommandLineRunner
import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication
import org.springframework.context.annotation.AnnotationConfigApplicationContext
import org.springframework.context.annotation.Bean


@SpringBootApplication
class SampleSpringBootCommandLineRunnerApplication(val producer:Producer){
    @Bean
    fun init() = CommandLineRunner {
        producer.produceMsg("送ったデータ")
    }
}

fun main(args: Array<String>) {
    val context = runApplication<SampleSpringBootCommandLineRunnerApplication>(*args)
    (context as AnnotationConfigApplicationContext).close()
}
