package com.example.sender

import com.amazon.sqs.javamessaging.SQSConnectionFactory
import com.amazonaws.auth.DefaultAWSCredentialsProviderChain
import com.amazonaws.regions.Region
import com.amazonaws.regions.Regions
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.jms.annotation.EnableJms
import org.springframework.jms.core.JmsTemplate

@Configuration
@EnableJms
class JmsSenderConfig{
    private val connectionFactory: SQSConnectionFactory by lazy {
        SQSConnectionFactory.builder()
                .withRegion(Region.getRegion(Regions.US_EAST_1))
                .withAWSCredentialsProvider(DefaultAWSCredentialsProviderChain())
                .build()
    }

    @Bean
    fun defaultJmsTemplate(): JmsTemplate {
        return JmsTemplate(this.connectionFactory)
    }
}