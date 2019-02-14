package com.example.sampleswagger

import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RestController

@RestController
class SampleController(
        val userRepository: UserRepository
) {
    @GetMapping("/users")
    fun getList(): List<User> {
        return userRepository.findAll()
    }

    @PostMapping("/users")
    fun create(@RequestBody user: User) {
        userRepository.save(user)
    }
}