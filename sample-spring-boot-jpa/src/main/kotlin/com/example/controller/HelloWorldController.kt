package com.example.controller

import com.example.service.SampleService
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RequestMethod
import org.springframework.web.bind.annotation.RestController

@RestController
class HelloWorldController(
        val sampleService: SampleService
) {
    @RequestMapping(value = ["/hello-world-jpa/{id}"], method = [RequestMethod.GET])
    fun helloWorldJpa(@PathVariable("id") id: Int): ResponseEntity<String> {
        return sampleService.getMessage(id)?.let {
            ResponseEntity.ok(it)
        } ?: ResponseEntity("not found!!",  HttpStatus.NOT_FOUND)
    }
}