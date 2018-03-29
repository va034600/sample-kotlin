package com.example.service

import org.springframework.data.redis.core.StringRedisTemplate
import org.springframework.stereotype.Service

@Service
class SampleServiceImpl(
        val stringRedisTemplate:StringRedisTemplate
):SampleService {
    override fun getMessage():String {
        stringRedisTemplate.opsForValue().set("test", "bbb");
        val result = stringRedisTemplate.opsForValue().get("test")
        return result ?: "not found"
    }
}