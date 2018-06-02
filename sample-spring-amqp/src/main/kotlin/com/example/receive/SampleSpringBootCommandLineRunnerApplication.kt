package com.example.receive

import org.springframework.amqp.core.Binding
import org.springframework.amqp.core.BindingBuilder
import org.springframework.amqp.core.Queue
import org.springframework.amqp.core.TopicExchange
import org.springframework.amqp.rabbit.connection.ConnectionFactory
import org.springframework.amqp.rabbit.listener.SimpleMessageListenerContainer
import org.springframework.amqp.rabbit.listener.adapter.MessageListenerAdapter
import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication
import org.springframework.context.annotation.Bean

@SpringBootApplication
class SampleSpringBootCommandLineRunnerApplication{
    @Bean
    internal fun queue(): Queue {
        return Queue(queueName, false)
    }

    @Bean
    internal fun exchange(): TopicExchange {
        return TopicExchange("spring-boot-exchange")
    }

    @Bean
    internal fun binding(queue: Queue, exchange: TopicExchange): Binding {
        return BindingBuilder.bind(queue).to(exchange).with("foo.bar.#")
    }

    @Bean
    internal fun container(connectionFactory: ConnectionFactory,
                           listenerAdapter: MessageListenerAdapter): SimpleMessageListenerContainer {
        val container = SimpleMessageListenerContainer()
        container.connectionFactory = connectionFactory
        container.setQueueNames(queueName)
        container.setMessageListener(listenerAdapter)
        return container
    }

    @Bean
    internal fun listenerAdapter(receiver: Receiver): MessageListenerAdapter {
        return MessageListenerAdapter(receiver, "receiveMessage")
    }

    companion object {

        internal val queueName = "spring-boot"
    }
}

fun main(args: Array<String>) {
    runApplication<SampleSpringBootCommandLineRunnerApplication>(*args)
}
