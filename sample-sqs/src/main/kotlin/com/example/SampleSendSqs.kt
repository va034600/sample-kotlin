package com.example

import com.amazonaws.regions.Regions
import com.amazonaws.services.sqs.AmazonSQSClientBuilder
import com.amazonaws.services.sqs.model.SendMessageRequest

object SampleSendSqs {

    private val QUEUE_NAME = "test_queue2.fifo"

    @Throws(Exception::class)
    @JvmStatic
    fun main(argv: Array<String>) {
//        val sqs = AmazonSQSClientBuilder.defaultClient()

        val sqs = AmazonSQSClientBuilder.standard()
                .withRegion(Regions.US_EAST_1)
                .build()

        val queueUrl = sqs.getQueueUrl(QUEUE_NAME).queueUrl

        for (i in 1..3) {
            val sendMessageRequest = SendMessageRequest()
                    .withQueueUrl(queueUrl)
                    .withMessageBody("おおおそうしん:$i")
//                    .withDelaySeconds(5)
                    .withMessageGroupId("messageGroup$i")
                    .withMessageDeduplicationId("messageDuplication$i")
            sqs.sendMessage(sendMessageRequest)
            println("そうしん$i")
        }
    }
}