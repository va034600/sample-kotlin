package com.example

import com.amazonaws.regions.Regions
import com.amazonaws.services.sqs.AmazonSQSClientBuilder
import com.amazonaws.services.sqs.model.ReceiveMessageRequest

object SampleReceiveSqs {

    private val QUEUE_NAME = "test_queue2.fifo"

    @Throws(Exception::class)
    @JvmStatic
    fun main(argv: Array<String>) {
//        val sqs = AmazonSQSClientBuilder.defaultClient()

        val sqs = AmazonSQSClientBuilder.standard()
                .withRegion(Regions.US_EAST_1)
                .build()

        val queueUrl = sqs.getQueueUrl(QUEUE_NAME).queueUrl
        val receiveMessageRequest = ReceiveMessageRequest(QUEUE_NAME)
        receiveMessageRequest.maxNumberOfMessages = 10
        // ロングポーリング
        receiveMessageRequest.waitTimeSeconds = 20
        val messages = sqs.receiveMessage(receiveMessageRequest).messages

        for (m in messages) {
            System.out.println("受信: " + m.body)
            sqs.deleteMessage(queueUrl, m.receiptHandle)
        }
    }
}