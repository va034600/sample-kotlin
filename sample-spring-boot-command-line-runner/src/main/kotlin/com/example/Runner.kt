package com.example


import org.springframework.boot.CommandLineRunner
import org.springframework.stereotype.Component

@Component
class Runner : CommandLineRunner {

    override fun run(vararg args: String) {
        println("init")
    }

}