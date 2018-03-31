package com.example

import com.example.service.SampleService
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RequestMethod
import org.springframework.web.bind.annotation.RestController

@RestController
class SampleController(
        val sampleService: SampleService
) {
    @RequestMapping(value = ["/sample-redis"], method = [RequestMethod.GET])
    fun sampleReids(): SampleDto? {
        return sampleService.getSampleDto();
    }

    @RequestMapping(value = ["/sample-redis2"], method = [RequestMethod.GET])
    fun sampleReids2(): SampleDto? {
        return sampleService.getSampleDto2();
    }
}