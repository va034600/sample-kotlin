package com.example

import org.springframework.batch.core.Job
import org.springframework.batch.core.Step
import org.springframework.batch.core.configuration.annotation.JobBuilderFactory
import org.springframework.batch.core.configuration.annotation.StepBuilderFactory
import org.springframework.batch.core.launch.support.RunIdIncrementer
import org.springframework.batch.repeat.RepeatStatus
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration

@Configuration
class BatchConfiguration
(
        val jobBuilderFactory: JobBuilderFactory,
        val stepBuilderFactory: StepBuilderFactory
) {
    @Bean
    fun step1(): Step {
        return stepBuilderFactory.get("step1")
                .tasklet { contribution, chunkContext ->
                    println("step1")
                    RepeatStatus.FINISHED
                }
                .build()
    }

    @Bean
    @Throws(Exception::class)
    fun job(step1: Step): Job {
        return jobBuilderFactory.get("job1")
                .incrementer(RunIdIncrementer())
                .start(step1)
                .build()
    }
}