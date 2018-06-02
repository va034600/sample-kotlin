package com.example

import com.rabbitmq.client.*

import java.io.IOException

object RabbitMQSample {

    private val QUEUE_NAME = "testQueue"

    @Throws(Exception::class)
    @JvmStatic
    fun main(argv: Array<String>) {
        val factory = ConnectionFactory()
        factory.host = "localhost"
        val connection = factory.newConnection()
        val channel = connection.createChannel()

        val message = "そそそそうしん!"
        channel.basicPublish("", QUEUE_NAME, null, message.toByteArray(charset("UTF-8")))

        val consumer = object : DefaultConsumer(channel) {
            @Throws(IOException::class)
            override fun handleDelivery(consumerTag: String?, envelope: Envelope?, properties: AMQP.BasicProperties?, body: ByteArray?) {
                val message = String( body ?: "not".toByteArray() , Charsets.UTF_8)
                println("受信 '$message'")
            }
        }
        channel.basicConsume(QUEUE_NAME, true, consumer)

        channel.close()
        connection.close()
    }
}