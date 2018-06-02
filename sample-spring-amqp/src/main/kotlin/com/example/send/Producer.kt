package com.example.send

import org.springframework.amqp.core.AmqpTemplate
import org.springframework.stereotype.Component


@Component
class Producer(val amqpTemplate: AmqpTemplate) {
    fun produceMsg(msg: String) {
        println("Sending message...")
        amqpTemplate.convertAndSend("spring-boot-exchange", "foo.bar.baz", msg)
        println("sent message...")
    }
}