package com.example.service

import com.example.SampleDto
import org.springframework.data.redis.core.RedisTemplate
import org.springframework.stereotype.Service

@Service
class SampleServiceImpl(
        val redisTemplate:RedisTemplate<String, SampleDto>
):SampleService {
    override fun getTestDto():SampleDto? {
        redisTemplate.opsForValue().set("test_dto", SampleDto(1, "aab"))
        return redisTemplate.opsForValue().get("test_dto")
    }
}