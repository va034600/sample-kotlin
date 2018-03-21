package com.example

import org.springframework.batch.core.configuration.annotation.EnableBatchProcessing
import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication

@EnableBatchProcessing
@SpringBootApplication
class SampleSpringBootBatchApplication

fun main(args: Array<String>) {
    runApplication<SampleSpringBootBatchApplication>(*args)
}
