package com.example.service

import com.example.SampleDto
import com.fasterxml.jackson.module.kotlin.jacksonObjectMapper
import org.springframework.data.redis.core.RedisTemplate
import org.springframework.stereotype.Service

@Service
class SampleServiceImpl(
        val redisTemplate:RedisTemplate<String, SampleDto>,
        val redisTemplate2:RedisTemplate<String, String>
):SampleService {
    override fun getSampleDto():SampleDto? {
        redisTemplate.opsForValue().set("test_dto", SampleDto(1, "aab"))
        return redisTemplate.opsForValue().get("test_dto")
    }

    override fun getSampleDto2():SampleDto? {
        val sampleDto = SampleDto(1, "aac")
        val om = jacksonObjectMapper()
        var jsonStr = om.writeValueAsString(sampleDto)

        redisTemplate2.opsForValue().set("test_dto3", jsonStr)
        val jsonStr2 = redisTemplate2.opsForValue().get("test_dto3")
        return om.readValue(jsonStr2 ?: "{}", SampleDto::class.java)
    }
}