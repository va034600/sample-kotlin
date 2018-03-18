package com.example.controller

import com.example.service.SampleService
import org.springframework.http.HttpStatus
import org.springframework.http.MediaType
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*

@RestController
class HelloWorldController(
        val sampleService: SampleService
) {
    @RequestMapping(value = ["/hello-world-jpa/{id}"], method = [RequestMethod.GET])
    fun helloWorldJpa(
            @PathVariable("id") id: Int
    ): ResponseEntity<String> {
        return sampleService.getMessage(id)?.let {
            ResponseEntity.ok(it)
        } ?: ResponseEntity("not found!!",  HttpStatus.NOT_FOUND)
    }

    @RequestMapping(value = ["/hello-world-jpa"], method = [RequestMethod.POST])
    fun helloWorldJpa(
            @ModelAttribute message:String
    ): ResponseEntity<String> {
        val id = sampleService.save(message)
        return ResponseEntity.ok(id.toString())
    }
}