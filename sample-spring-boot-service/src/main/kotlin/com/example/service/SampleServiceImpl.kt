package com.example.service

import org.springframework.stereotype.Service

@Service
class SampleServiceImpl:SampleService {
    override fun getMessage():String {
        return "message!!"
    }
}