package com.example

import org.springframework.http.MediaType
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RequestMethod
import org.springframework.web.bind.annotation.RestController

@RestController
class HelloWorldJsonController {
    @RequestMapping(value="/hello-world-json", method=[RequestMethod.GET], produces=[MediaType.APPLICATION_JSON_VALUE])
    fun hello():SampleBean{
        val bean = SampleBean(123,"aaa");
        return bean;
    }
}