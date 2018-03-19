package com.example

import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RequestMethod
import org.springframework.web.bind.annotation.RestController

@RestController
class HelloWorldController {
    @RequestMapping(value=["/hello-world"], method=[RequestMethod.GET])
    fun helloWorld():String{
        return "Hello World";
    }
}