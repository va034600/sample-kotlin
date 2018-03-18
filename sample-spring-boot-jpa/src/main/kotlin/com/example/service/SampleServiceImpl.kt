package com.example.service

import com.example.entity.SampleEntity
import com.example.repository.SampleRepository
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional

@Service
class SampleServiceImpl(
        val sampleRepository: SampleRepository
) : SampleService {
    @Transactional(readOnly = true)
    override fun getMessage(id: Int): String? {
        val sampleEntity = sampleRepository.findById(id)

        if(!sampleEntity.isPresent){
            return null
        }

        return sampleEntity?.let {
            it.get().message ?: "empty column"
        }
    }

    @Transactional(timeout = 10)
    override fun save(message: String): Int {
        val sampleEntity = SampleEntity(message = message)
        val sampleEntity2 = sampleRepository.save(sampleEntity)
        return sampleEntity2.id
    }
}