package com.example.service

interface SampleService {
    fun getMessage(id:Int):String?

    fun save(message: String):Int
}