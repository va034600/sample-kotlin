package com.example

import com.fasterxml.jackson.module.kotlin.jacksonObjectMapper
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.data.redis.connection.jedis.JedisConnectionFactory
import org.springframework.data.redis.core.RedisTemplate
import org.springframework.data.redis.serializer.Jackson2JsonRedisSerializer
import org.springframework.data.redis.serializer.StringRedisSerializer

@Configuration
class SampleConfiguration {
    @Bean
    fun jedisConnectionFactory(): JedisConnectionFactory {
        return JedisConnectionFactory()
    }

    @Bean
    fun redisTemplate(): RedisTemplate<String, SampleDto> {
        val template  = RedisTemplate<String, SampleDto>()
        template.setConnectionFactory(jedisConnectionFactory())
        template.keySerializer = StringRedisSerializer()

//        template.valueSerializer = Jackson2JsonRedisSerializer<SampleDto>(SampleDto::class.java)

        val jackson2JsonRedisSerializer = Jackson2JsonRedisSerializer(SampleDto::class.java)
        val om = jacksonObjectMapper()
        jackson2JsonRedisSerializer.setObjectMapper(om)
        template.valueSerializer = jackson2JsonRedisSerializer

        return template;
    }

    @Bean
    fun redisTemplate2(): RedisTemplate<String, String> {
        val template  = RedisTemplate<String, String>()
        template.setConnectionFactory(jedisConnectionFactory())
        template.keySerializer = StringRedisSerializer()
        template.valueSerializer = StringRedisSerializer()
        return template;
    }
}