package com.example.samplespringsecurityredis

import org.springframework.security.core.annotation.AuthenticationPrincipal
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RestController

@RestController
class HelloWorldJsonController {
    @GetMapping(value=["pub/test"])
    fun pub(): String {
        return "pub"
    }

    @GetMapping(value=["prv/test"])
    fun prv(@AuthenticationPrincipal loginUser: SimpleLoginUser): String {
        return loginUser.username
    }
}
