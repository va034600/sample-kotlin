package com.example.receiver

import org.springframework.jms.annotation.JmsListener
import org.springframework.messaging.handler.annotation.Payload
import org.springframework.stereotype.Component

@Component
class Receiver {
    @JmsListener(destination = "test_queue2.fifo")
    fun processMessageA(@Payload message:String) {
        println("受信 $message");
    }
}