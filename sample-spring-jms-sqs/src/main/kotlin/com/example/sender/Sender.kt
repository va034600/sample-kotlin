package com.example.sender

import com.amazon.sqs.javamessaging.SQSMessagingClientConstants
import org.springframework.jms.core.JmsTemplate
import org.springframework.stereotype.Component

@Component
class Sender(val defaultJmsTemplate: JmsTemplate) {
    fun send(message: String) {
        defaultJmsTemplate.send("test_queue2.fifo") { session ->
            val createMessage = session.createTextMessage(message)
            createMessage.setStringProperty(SQSMessagingClientConstants.JMSX_GROUP_ID, "messageGroup1")
            createMessage.setStringProperty(SQSMessagingClientConstants.JMS_SQS_DEDUPLICATION_ID, "1" + System.currentTimeMillis())
            createMessage
        }
    }
}