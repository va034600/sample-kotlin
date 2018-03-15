package com.example

import com.example.service.SampleService
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RequestMethod
import org.springframework.web.bind.annotation.RestController

@RestController
class HelloWorldController(
        val sampleService: SampleService
) {
    @RequestMapping(value = ["/hello-world-service"], method = [RequestMethod.GET])
    fun hello(): String {
        return sampleService.getMessage();
    }
}