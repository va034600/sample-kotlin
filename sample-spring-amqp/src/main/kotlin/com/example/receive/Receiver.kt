package com.example.receive


import java.util.concurrent.CountDownLatch
import org.springframework.stereotype.Component

@Component
class Receiver {

    val latch = CountDownLatch(1)

    fun receiveMessage(message: String) {
        println("Received <$message>")
        latch.countDown()
    }

}