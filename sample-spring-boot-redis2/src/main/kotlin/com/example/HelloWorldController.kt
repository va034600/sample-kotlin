package com.example

import com.example.service.SampleService
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RequestMethod
import org.springframework.web.bind.annotation.RestController

@RestController
class HelloWorldController(
        val sampleService: SampleService
) {
    @RequestMapping(value = ["/hello-world-redis"], method = [RequestMethod.GET])
    fun helloWorldReids(): SampleDto? {
        return sampleService.getSampleDto();
    }

    @RequestMapping(value = ["/hello-world-redis2"], method = [RequestMethod.GET])
    fun helloWorldReids2(): SampleDto? {
        return sampleService.getSampleDto2();
    }
}